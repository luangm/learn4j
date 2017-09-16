package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.*;

/**
 * This is the base interface for a ComputeGraph Visitor
 * <p>
 * New Visitors should extend BaseComputeVisitor
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public interface ComputeVisitor {

    void visit(ComputeNode node);

    void visitAdd(AddNode node);

    void visitMatMul(MatMulNode node);

    void visitParameter(ParameterNode node);

    void visitMultiply(MultiplyNode node);
}
