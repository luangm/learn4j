package io.luan.learn4j.structure;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Graph {

    /**
     * Adds a particular Expression to the graph.
     * The Graph should first check if a equivalent is already in the graph,
     * If true, the original exp is returned, the new exp is discarded.
     * Otherwise, the new exp is inserted to the graph and returned.
     * <p>
     * The user should only keep references to the Expressions returned by this, NOT the input.
     */
    Expression add(Expression exp);

    /**
     * Map of all Expression Nodes within the Graph.
     * Key = Expression.ID
     * Value = Expression
     */
    Map<Integer, Expression> getNodes();

}
