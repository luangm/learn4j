package io.luan.learn4j.structure;

import io.luan.learn4j.core.Tensor;
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
     * Evaluate the value of this expression using the graph's active Session.
     */
    Tensor eval();

    /**
     * Returns the gradient wrt to the target.
     * <p>
     * Note, Since this is only for storing the compute graph structure, this interface does NOT enforce
     * the correctness of the gradient.
     */
    Expression getGradient(Expression target);

    Expression getGradient();

    /**
     * Returns the containing graph
     */
    Graph getGraph();

    /**
     * Attach to a graph
     */
    void attach(Graph graph);

    /**
     * Returns the unique ID of the Expression.
     */
    int getId();

    /**
     * Returns the name of the expression.
     */
    String getName();

    /**
     * Returns the shape of the expression
     */
    int[] getShape();

    /**
     * The current state of the expression
     */
    ExpressionState getState();

    /**
     * Modifies the state
     */
    void setState(ExpressionState state);

    /**
     * Returns the type of the Expression
     */
    ExpressionType getType();

    /**
     * Returns the current value of the Expression at the active session only.
     * Or Null if the current value is not yet evaluated.
     */
    Tensor getValue();

    /**
     * This forces setting the session's value for this expression.
     */
    void setValue(Tensor value);

    /**
     * Sets the gradient expression of the current Expresson wrt the target.
     * <p>
     * Note, this is only storing the relation, it does NOT enforce the correctness.
     */
    void setGradient(Integer targetId, Expression gradient);
}
