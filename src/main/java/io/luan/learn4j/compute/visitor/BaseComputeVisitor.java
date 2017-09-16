package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.*;
import io.luan.learn4j.expression.Add;
import io.luan.learn4j.expression.MatMul;
import io.luan.learn4j.expression.Multiply;
import io.luan.learn4j.expression.Parameter;

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
    }
    public void visitAdd(AddNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitParameter(ParameterNode node) {
        // by default do nothing
    }

    public void visitMatMul(MatMulNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    public void visitMultiply(MultiplyNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);
    }
}
