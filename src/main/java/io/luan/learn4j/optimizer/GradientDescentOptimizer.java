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
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 21/09/2017.
 */
public class GradientDescentOptimizer {

    private ExpressionFactory factory;
    @Getter
    private Graph graph;
    @Getter
    private Expression learnRate;

    public GradientDescentOptimizer(Graph graph, double learnRate) {
        this.graph = graph;
        this.factory = new ExpressionFactory(graph);
        this.learnRate = factory.parameter(Tensor.scalar(learnRate));
    }

    public Expression minimize(Expression loss) {
        DependencyVisitor depVisitor = new DependencyVisitor();
        loss.accept(depVisitor);
        List<Expression> params = new ArrayList<>();
        for (Expression exp : depVisitor.getDependencies()) {
            if (exp.getType() == ExpressionType.Parameter) {
                params.add(exp);
            }
        }

        ReverseGradientVisitor visitor = new ReverseGradientVisitor(this.graph);
        visitor.visit(loss, null);

        Expression[] paramList = params.toArray(new Expression[params.size()]);
        Expression[] valueList = new Expression[params.size()];
        Expression[] groupList = new Expression[paramList.length + 1];

        for (int i = 0; i < paramList.length; i++) {
            val exp = params.get(i);
            val grad = loss.getGradient(exp);
            val mul = factory.multiply(this.learnRate, grad);
            val sub = factory.subtract(exp, mul);
            valueList[i] = sub;
            groupList[i] = sub;
        }

        groupList[paramList.length] = factory.multiAssign(paramList, valueList);
        return factory.group(groupList);
    }
}
