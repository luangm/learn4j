package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.*;
import io.luan.learn4j.compute.impl.*;

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

    void visitSubtract(SubtractNode node);

    void visitPower(PowerNode node);

    void visitReduceMean(ReduceMeanNode node);

    void visitSquare(SquareNode node);
}
