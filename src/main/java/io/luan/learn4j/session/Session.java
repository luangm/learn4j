package io.luan.learn4j.session;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Session {

    /**
     * Gets the Tensor flow mode.
     */
    FlowMode getFlowMode();

    /**
     * Returns the current graph for this session
     */
    Graph getGraph();

    /**
     * Runs a Graph starting at the exp node
     */
    Tensor run(Expression exp);

    /**
     * Runs a Graph starting at the exp node, with the supplied feed map
     */
    Tensor run(Expression exp, Map<Expression, Tensor> feed);
}
