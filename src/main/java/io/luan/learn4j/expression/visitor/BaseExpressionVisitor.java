package io.luan.learn4j.expression.visitor;

import io.luan.learn4j.expression.*;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseExpressionVisitor implements ExpressionVisitor {

    public void visit(Expression node) {
        switch(node.getType()) {
            case Add.TYPE:
                visitAdd((Add) node);
                break;
            case Parameter.TYPE:
                visitParameter((Parameter) node);
                break;
            case MatMul.TYPE:
                visitMatMul((MatMul) node);
                break;
            case Multiply.TYPE:
                visitMultiply((Multiply) node);
                break;
            case Variable.TYPE:
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
