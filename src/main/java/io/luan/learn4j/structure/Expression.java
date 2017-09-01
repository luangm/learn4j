package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeNode;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Map;

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

    Iterable<Expression> getDependencies();

    ComputeNode getComputeNode();

}
