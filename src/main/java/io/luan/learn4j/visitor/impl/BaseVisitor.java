package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.*;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    public void visit(Expression node) {
        switch(node.getType()) {
            case Add:
                visitAdd((Add) node);
                break;
            case Parameter:
                visitParameter((Parameter) node);
                break;
            case MatMul:
                visitMatMul((MatMul) node);
                break;
            case Multiply:
                visitMultiply((Multiply) node);
                break;
            case Variable:
                visitVariable((Variable) node);
                break;
        }
    }

    public void visitAdd(Add node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitParameter(Parameter node) {
        // by default do nothing
    }

    public void visitMatMul(MatMul node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitMultiply(Multiply node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitPower(Power node) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    public void visitSubtract(Subtract node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitReduceMean(ReduceMean node) {
        node.getBase().accept(this);
    }

    public void visitSquare(Square node) {
        node.getBase().accept(this);
    }

    protected void visitVariable(Variable node) {
        // do nothing
    }

}
