package io.luan.learn4j.compute;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ParameterNode extends BaseNode {

    private final static List<ComputeNode> EMPTY = new ArrayList<ComputeNode>();

    public ParameterNode(INDArray value) {
        super(value);
    }

    public List<ComputeNode> getDependencies() {
        return EMPTY;
    }

    public ComputeNode getGradient(ParameterNode node) {
        if (this == node) {
            return ConstantNode.ones(1, 1);
        }

        return null;
    }
}
