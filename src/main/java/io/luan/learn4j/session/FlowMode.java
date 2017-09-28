package io.luan.learn4j.session;

/**
 * @author Guangmiao Luan
 * @since 24/09/2017.
 */
public enum FlowMode {

    /**
     * In this mode, all the tensors are stored at each compute node.
     * Advantage: Caching a subgraph allows some computations to run faster on later iterations
     * Disadvantage: Uses more memory
     */
    Store,

    /**
     * In this mode, Tensor are modified in-place, and only necessary tensors are outputted.
     *
     * Advantage: Use only necessary amount of memory, faster due to less array copying
     */
    Flow


}
