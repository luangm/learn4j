package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;

/**
 * Expression is the primary Node interface for Graph
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Expression {

    /**
     * Returns the name of the expression.
     *
     * The name can be null, by which the expression node cannot be found by name in a Graph
     */
    String getName();

    /**
     * Returns the compute node for this Expression.
     * This should be for internal use only
     */
    ComputeNode getComputeNode();

    /**
     * Generic Visitor Pattern
     */
    void accept(ExpressionVisitor visitor);
}
