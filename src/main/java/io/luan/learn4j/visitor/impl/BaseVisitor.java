package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    public void visit(Expression node, Object... params) {
        switch (node.getType()) {
            case Add:
                visitAdd((Add) node, params);
                break;
            case Assign:
                visitAssign((Assign)node, params);
                break;
            case Constant:
                visitConstant((Constant) node, params);
                break;
            case Group:
                visitGroup((Group) node, params);
                break;
            case MatMul:
                visitMatMul((MatMul) node, params);
                break;
            case Multiply:
                visitMultiply((Multiply) node, params);
                break;
            case Parameter:
                visitParameter((Parameter) node, params);
                break;
            case Power:
                visitPower((Power) node, params);
                break;
            case ReduceMean:
                visitReduceMean((ReduceMean) node, params);
                break;
            case Square:
                visitSquare((Square) node, params);
                break;
            case Subtract:
                visitSubtract((Subtract) node, params);
                break;
            case Variable:
                visitVariable((Variable) node, params);
                break;
        }
    }

    protected void visitGroup(Group node, Object... params) {
        for (Expression exp : node.getExpList()) {
            exp.accept(this);
        }
    }

    protected void visitAdd(Add node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitConstant(Constant node, Object... params) {
        // do nothing
    }

    protected void visitMatMul(MatMul node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitMultiply(Multiply node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitParameter(Parameter node, Object... params) {
        // by default do nothing
    }

    protected void visitPower(Power node, Object... params) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    protected void visitReduceMean(ReduceMean node, Object... params) {
        node.getBase().accept(this);
    }

    protected void visitSquare(Square node, Object... params) {
        node.getBase().accept(this);
    }

    protected void visitSubtract(Subtract node, Object... params) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitVariable(Variable node, Object... params) {
        // do nothing
    }

    protected void visitAssign(Assign node, Object... params) {
        node.getNewValue().accept(this);
    }
}
