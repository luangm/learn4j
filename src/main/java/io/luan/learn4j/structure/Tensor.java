package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeNode;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Map;

/**
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Tensor implements Expression {
    public String getName() {
        return null;
    }

    public Iterable<Expression> getDependencies() {
        return null;
    }

    public INDArray getValue() {
        return null;
    }

    public INDArray evaluate(Map<String, INDArray> feedDict) {
        return null;
    }

    public ComputeNode getComputeNode() {
        return null;
    }
}
