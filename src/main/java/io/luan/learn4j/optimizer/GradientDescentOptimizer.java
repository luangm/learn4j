package io.luan.learn4j.optimizer;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.visitor.impl.DependencyVisitor;
import lombok.Getter;

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

    public GradientDescentOptimizer(Graph graph, double learnRate) {
        this.graph = graph;
        this.learnRate = ExpressionFactory.createConstant("learn_rate", Tensor.scalar(learnRate));
    }

    public Expression minimize(Expression loss) {

        DependencyVisitor depVisitor = new DependencyVisitor();
        loss.accept(depVisitor);

        List<Expression> assignList = new ArrayList<>();

        for (Expression exp : depVisitor.getDependencies()) {
            if (exp.getType() == ExpressionType.Parameter) {
                Expression grad = loss.getGradient(exp);
                Expression newGrad = ExpressionFactory.createMultiply(null, grad, this.learnRate);
                Expression sub = ExpressionFactory.createSubtract("", exp, newGrad);
                Expression assign = ExpressionFactory.createAssign("", exp, sub);
                assignList.add(assign);
            }
        }


        return ExpressionFactory.createGroup("TrainStep", assignList.toArray(new Expression[assignList.size()]));

    }
}
