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
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitAssign(Assign node, Object... params) {
        node.getNewValue().accept(this);
    }

    public void visitConstant(Constant node, Object... params) {
        // do nothing
    }

    public void visitGroup(Group node, Object... params) {
        for (Expression exp : node.getExpList()) {
            exp.accept(this);
        }
    }

    public void visitMatMul(MatMul node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitMultiply(Multiply node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitParameter(Parameter node, Object... params) {
        // by default do nothing
    }

    public void visitPower(Power node, Object... params) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    public void visitReduceMean(ReduceMean node, Object... params) {
        node.getBase().accept(this);
    }

    public void visitSquare(Square node, Object... params) {
        node.getBase().accept(this);
    }

    public void visitSubtract(Subtract node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitVariable(Variable node, Object... params) {
        // do nothing
    }
}
