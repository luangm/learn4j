package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.structure.impl.Add;
import io.luan.learn4j.structure.impl.Constant;
import io.luan.learn4j.structure.impl.Parameter;

/**
 * Apply a forward-mode AD pass through a compute graph,
 *
 * @author Guangmiao Luan
 * @since 06/10/2017.
 */
public class ForwardGradientVisitor extends BaseVisitor {

    private Expression target;

    public ForwardGradientVisitor(Expression target) {
        this.target = target;
    }

    private String createNameForGradient(Expression node) {
        String gradName = node.getName() + "/grad_" + target.getName();
        return gradName;
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        super.visitAdd(node);
        String gradName = createNameForGradient(node);
        Expression leftGrad = node.getLeft().getGradient(target);
        Expression rightGrad = node.getRight().getGradient(target);
        Expression gradient = ExpressionFactory.createAdd(gradName, leftGrad, rightGrad);
        node.setGradient(target, gradient);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        Expression gradient = Constant.ZERO;
        node.setGradient(target, gradient);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        Expression gradient = target == node ? Constant.ONE : Constant.ZERO;
        node.setGradient(target, gradient);
    }
}
