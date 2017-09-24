package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.*;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public abstract class BaseComputeVisitor implements ComputeVisitor {

    public void visit(ComputeNode node) {

        if (node instanceof ParameterNode) {
            visitParameter((ParameterNode) node);
        }

        if (node instanceof AddNode) {
            visitAdd((AddNode) node);
        }

        if (node instanceof MatMulNode) {
            visitMatMul((MatMulNode) node);
        }

        if (node instanceof MultiplyNode) {
            visitMultiply((MultiplyNode) node);
        }

        if (node instanceof SubtractNode) {
            visitSubtract((SubtractNode) node);
        }

        if (node instanceof PowerNode) {
            visitPower((PowerNode) node);
        }

        if (node instanceof ReduceMeanNode) {
            visitReduceMean((ReduceMeanNode) node);
        }

        if (node instanceof SquareNode) {
            visitSquare((SquareNode) node);
        }

        if (node instanceof VariableNode) {
            visitVariable((VariableNode) node);
        }
    }

    public void visitAdd(AddNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitMatMul(MatMulNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitParameter(ParameterNode node) {
        // by default do nothing
    }

    public void visitMultiply(MultiplyNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitSubtract(SubtractNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitPower(PowerNode node) {
        node.getBase().accept(this);
        node.getPower().accept(this);
    }

    public void visitReduceMean(ReduceMeanNode node) {
        node.getBase().accept(this);
    }

    public void visitSquare(SquareNode node) {
        node.getBase().accept(this);
    }

    public void visitVariable(VariableNode node) {
        // nothing
    }
}
