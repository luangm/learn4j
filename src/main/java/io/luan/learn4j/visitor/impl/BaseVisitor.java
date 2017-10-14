package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    public void visitAdd(Add node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    public void visitAssign(Assign node, Object... params) {
        node.getNewValue().accept(this, params);
    }

    public void visitConstant(Constant node, Object... params) {
        // do nothing
    }

    public void visitFill(Fill node, Object... params) {
        // do nothing
    }

    public void visitGroup(Group node, Object... params) {
        for (Expression exp : node.getExpList()) {
            exp.accept(this, params);
        }
    }

    public void visitMatMul(MatMul node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    public void visitMultiply(Multiply node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    public void visitNegate(Negate node, Object... params) {
        node.getBase().accept(this, params);
    }

    public void visitParameter(Parameter node, Object... params) {
        // by default do nothing
    }

    public void visitPower(Power node, Object... params) {
        node.getBase().accept(this, params);
        node.getPower().accept(this, params);
    }

    public void visitReduceMean(ReduceMean node, Object... params) {
        node.getBase().accept(this, params);
    }

    public void visitReduceSum(ReduceSum node, Object... params) {
        node.getBase().accept(this, params);
    }

    public void visitSigmoid(Sigmoid node, Object... params) {
        node.getBase().accept(this, params);
    }

    public void visitSigmoidGrad(SigmoidGrad node, Object[] params) {
        node.getBase().accept(this, params);
    }

    public void visitSquare(Square node, Object... params) {
        node.getBase().accept(this, params);
    }

    public void visitSubtract(Subtract node, Object... params) {
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    public void visitVariable(Variable node, Object... params) {
        // do nothing
    }
}
