package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.structure.impl.*;

/**
 * @author Guangmiao Luan
 * @since 06/10/2017.
 */
public class ReverseGradientVisitor extends BaseVisitor {

    public ReverseGradientVisitor() {

    }

    @Override
    public void visitAdd(Add node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        Expression leftGrad = grad;
        Expression rightGrad = grad;

        node.getLeft().setGradient(node, leftGrad);
        node.getRight().setGradient(node, rightGrad);

        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        String leftGradName = node.getName() + "/grad_" + node.getLeft().getName();
        String rightGradName = node.getName() + "/grad_" + node.getRight().getName();

        Expression leftGrad = ExpressionFactory.createMatMul(leftGradName, grad, node.getRight(), false, true);
        Expression rightGrad = ExpressionFactory.createMatMul(rightGradName, node.getLeft(), grad, true, false);

        node.getLeft().setGradient(node, leftGrad);
        node.getRight().setGradient(node, rightGrad);

        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String leftGradName = node.getName() + "/grad_" + node.getLeft().getName();
        String rightGradName = node.getName() + "/grad_" + node.getRight().getName();

        Expression leftGrad = ExpressionFactory.createMultiply(leftGradName, grad, node.getRight());
        Expression rightGrad = ExpressionFactory.createMultiply(rightGradName, node.getLeft(), grad);

        node.getLeft().setGradient(node, leftGrad);
        node.getRight().setGradient(node, rightGrad);

        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        node.getBase().setGradient(node, grad);
        node.getBase().accept(this, grad);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        node.getBase().setGradient(node, grad);
        node.getBase().accept(this, grad);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String gradName = node.getName() + "/grad_" + node.getBase().getName();
        String sigGradName = gradName + "/sigGrad";

        Expression sigGrad = ExpressionFactory.createSigmoidGrad(sigGradName, node.getBase());
        Expression result = ExpressionFactory.createMultiply(gradName, grad, sigGrad);

        node.getBase().setGradient(node, result);

        node.getBase().accept(this, result);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String gradName = node.getName() + "/grad_" + node.getBase().getName();

        String mulName = gradName + "/mul";
        Expression mul = ExpressionFactory.createMultiply(mulName, node.getBase(), Constant.TWO);

        Expression result = ExpressionFactory.createMultiply(gradName, grad, mul);

        node.getBase().setGradient(node, result);

        node.getBase().accept(this, result);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String rightGradName = node.getName() + "/grad_" + node.getRight().getName();

        Expression leftGrad = grad;
        Expression rightGrad = ExpressionFactory.createNegate(rightGradName, grad);

        node.getLeft().setGradient(node, leftGrad);
        node.getRight().setGradient(node, rightGrad);

        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    private static Expression getGradientOrDefault(Expression node, Object... params) {
        if (params.length > 0) {
            return (Expression) params[0];
        }

        return new Fill("FILL", 1, node.getShape());
    }

}
