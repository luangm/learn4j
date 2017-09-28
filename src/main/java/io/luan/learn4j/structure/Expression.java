package io.luan.learn4j.structure;

import io.luan.learn4j.visitor.Visitor;

/**
 * Expression is the primary Node interface for Graph
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Expression {

    /**
     * Generic Visitor Pattern
     */
    void accept(Visitor visitor);

    /**
     * Returns a Gradient expression wrt the Target expression
     * <p>
     * Note this is only for creating the Expression, not attaching it to the Graph.
     * Use a GradientVisitor for that
     */
    Expression getGradient(Expression target);

    /**
     * Returns the name of the expression.
     * <p>
     * The name can be null, by which the expression node cannot be found by name in a Graph
     */
    String getName();

    /**
     * Set the name of the expression.
     */
    void setName(String name);

    /**
     * Returns the type of the Expression
     */
    ExpressionType getType();
}
