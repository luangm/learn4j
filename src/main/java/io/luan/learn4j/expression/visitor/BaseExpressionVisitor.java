package io.luan.learn4j.expression.visitor;

import io.luan.learn4j.expression.*;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseExpressionVisitor implements ExpressionVisitor {

    public void visit(Expression node) {
        if (node instanceof Add) {
            visitAdd((Add) node);
        }

        if (node instanceof Parameter) {
            visitParameter((Parameter) node);
        }

        if (node instanceof MatMul) {
            visitMatMul((MatMul) node);
        }

        if (node instanceof Multiply) {
            visitMultiply((Multiply) node);
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

    public void visitSubtract(Subtract node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitPower(Power node) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    public void visitReduceMean(ReduceMean node) {
        node.getBase().accept(this);
    }

    public void visitSquare(Square node) {
        node.getBase().accept(this);
    }
}
