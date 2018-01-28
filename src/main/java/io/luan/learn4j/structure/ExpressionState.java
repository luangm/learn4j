package io.luan.learn4j.structure;

/**
 * The state of the Expression within the graph
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public enum ExpressionState {

    /**
     * When an expression is created but not added to a graph
     */
    Detached,

    /**
     * When an expression is attached to a graph and value is not yet evaluated
     */
    Attached,

    /**
     * When the value of the expression is modified
     */
    Modified,

    /**
     * When one of its dependencies are modified or invalidated
     */
    Invalidated,

    /**
     * When the value of the expression is fully evaluated.
     * And all of its dependencies are also evaluated.
     */
    Evaluated

}
