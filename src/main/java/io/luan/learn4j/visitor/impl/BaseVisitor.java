package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    public void visit(Expression node) {
        switch (node.getType()) {
            case Add:
                visitAdd((Add) node);
                break;
            case Assign:
                visitAssign((Assign)node);
                break;
            case Constant:
                visitConstant((Constant) node);
                break;
            case Group:
                visitGroup((Group) node);
                break;
            case MatMul:
                visitMatMul((MatMul) node);
                break;
            case Multiply:
                visitMultiply((Multiply) node);
                break;
            case Parameter:
                visitParameter((Parameter) node);
                break;
            case Power:
                visitPower((Power) node);
                break;
            case ReduceMean:
                visitReduceMean((ReduceMean) node);
                break;
            case Square:
                visitSquare((Square) node);
                break;
            case Subtract:
                visitSubtract((Subtract) node);
                break;
            case Variable:
                visitVariable((Variable) node);
                break;

        }
    }

    protected void visitGroup(Group node) {
        for (Expression exp : node.getExpList()) {
            exp.accept(this);
        }
    }

    protected void visitAdd(Add node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitConstant(Constant node) {
        // do nothing
    }

    protected void visitMatMul(MatMul node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitMultiply(Multiply node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitParameter(Parameter node) {
        // by default do nothing
    }

    protected void visitPower(Power node) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    protected void visitReduceMean(ReduceMean node) {
        node.getBase().accept(this);
    }

    protected void visitSquare(Square node) {
        node.getBase().accept(this);
    }

    protected void visitSubtract(Subtract node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    protected void visitVariable(Variable node) {
        // do nothing
    }

    protected void visitAssign(Assign node) {
        node.getNewValue().accept(this);
    }
}
