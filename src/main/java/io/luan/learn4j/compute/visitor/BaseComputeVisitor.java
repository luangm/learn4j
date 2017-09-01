package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.AddNode;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.MatMulNode;
import io.luan.learn4j.compute.ParameterNode;

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
    }

    abstract void visitAdd(AddNode node);

    abstract void visitMatMul(MatMulNode node);

    abstract void visitParameter(ParameterNode node);
}
