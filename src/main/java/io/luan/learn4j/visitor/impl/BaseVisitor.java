package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    @Override
    public void visitAdd(Add node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        node.getNewValue().accept(this, params);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        // do nothing
    }

    @Override
    public void visitFill(Fill node, Object... params) {
        // do nothing
    }

    @Override
    public void visitGroup(Group node, Object... params) {
        for (Expression exp : node.getExpList()) {
            exp.accept(this, params);
        }
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        // by default do nothing
    }

    @Override
    public void visitPower(Power node, Object... params) {
        node.getBase().accept(this, params);
        node.getPower().accept(this, params);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object[] params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        // do nothing
    }
}
