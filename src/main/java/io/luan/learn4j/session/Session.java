package io.luan.learn4j.session;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;

import java.util.Map;

/**
 * A Session object stores all of the Tensor values for all expression in the Graph.
 * The only exception is Constant and Parameter, where the values are stored in the Expression itself.
 * <p>
 * The relationship between a Graph and a Session is 1:N.
 * e.g. multiple sessions can share the same graph
 * But a graph can only have ONE ACTIVE session at the same time.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Session {

    /**
     * Evaluate the value of the Expression
     */
    Tensor eval(Expression exp);

    /**
     * Runs a Graph starting at the exp node, with the supplied feed map
     */
    Tensor eval(Expression exp, Map<Expression, Tensor> feed);

    /**
     * Returns the current graph for this session
     */
    Graph getGraph();

    /**
     * Gets the value of the exp within this session
     */
    Tensor getValue(Expression exp);

    void setValue(Expression exp, Tensor value);

}
