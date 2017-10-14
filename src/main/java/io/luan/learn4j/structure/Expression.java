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
     * Generic Visitor Pattern with passed params
     */
    void accept(Visitor visitor, Object... params);

    /**
     * Returns the gradient wrt to the target.
     * <p>
     * Note, Since this is only for storing the compute graph structure, this interface does NOT enforce
     * the correctness of the gradient.
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
     * Returns the rank of the expression
     */
    int getRank();

    /**
     * Returns the shape of the expression
     */
    int[] getShape();

    /**
     * Returns the type of the Expression
     */
    ExpressionType getType();

    /**
     * Sets the gradient expression of the current Expresson wrt the target.
     * <p>
     * Note, this is only storing the relation, it does NOT enforce the correctness.
     */
    void setGradient(Expression target, Expression gradient);
}
