package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeNode;
import lombok.Data;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Collections;
import java.util.Map;

/**
 * The Variable node is differentiable with Gradient Descend Algorithm
 * <p>
 * NOTE this is not the same as Variable in TensorFlow. This is equivalent to Placeholder in Tensorflow
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
@Data
public class Variable implements Expression {

    private String name;
    private int[] shape;

    public Variable(String name, int[] shape) {
        this.name = name;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public Iterable<Expression> getDependencies() {
        return Collections.emptyList();
    }

    public INDArray getValue() {
        return null;
    }

    public INDArray evaluate(Map<String, INDArray> feedDict) {
        return feedDict.get(name);
    }

    public ComputeNode getComputeNode() {
        return null;
    }
}
