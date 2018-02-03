package io.luan.learn4j.optimizer;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.visitor.impl.DependencyVisitor;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 21/09/2017.
 */
public class GradientDescentOptimizer {

    @Getter
    private Graph graph;

    @Getter
    private Expression learnRate;

    private ExpressionFactory factory;

    public GradientDescentOptimizer(Graph graph, double learnRate) {
        this.graph = graph;
        this.factory = new ExpressionFactory(graph);
        this.learnRate = factory.parameter(Tensor.scalar(learnRate));
    }

    public Expression minimize(Expression loss) {
        DependencyVisitor depVisitor = new DependencyVisitor();
        loss.accept(depVisitor);

        ReverseGradientVisitor visitor = new ReverseGradientVisitor(this.graph);
        visitor.visit(loss, null);

        List<Expression> assignList = new ArrayList<>();
        for (Expression exp : depVisitor.getDependencies()) {
            if (exp.getType() == ExpressionType.Parameter) {
                val grad = loss.getGradient(exp);
                val mul = factory.multiply(this.learnRate, grad);
                val sub = factory.subtract(exp, mul);
                val assign = factory.assign(exp, sub);
                assignList.add(assign);
            }
        }

        return factory.group(assignList.toArray(new Expression[assignList.size()]));

    }
}
