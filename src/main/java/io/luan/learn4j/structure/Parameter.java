package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.ParameterNode;
import lombok.Data;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Collections;
import java.util.Map;

/**
 * Parameter is holder for parameters to be updated on each gradient descend run
 * <p>
 * This is equivalent to TensorFlow.Variable
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
@Data
public class Parameter implements Expression {

    private INDArray ndArray;
    private String name;
    private ComputeNode computeNode;

    public Parameter(String name, INDArray ndArray) {
        this.ndArray = ndArray;
        this.name = name;
        this.computeNode = new ParameterNode(ndArray);
    }

    public String getName() {
        return name;
    }

    public Iterable<Expression> getDependencies() {
        return Collections.emptyList();
    }

    public INDArray getValue() {
        return ndArray;
    }

    public INDArray evaluate(Map<String, INDArray> feedDict) {
        return ndArray;
    }

    public ComputeNode getComputeNode() {
        return computeNode;
    }
}
