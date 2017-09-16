package io.luan.learn4j.compute;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ParameterNode extends BaseNode {

    public ParameterNode(String name, INDArray value) {
        super(name, value);
    }

    ComputeNode createGradientNode(String target) {
        if (this.getName().equals(target)) {
            // self
            return ConstantNode.IDENTITY;
        }
        return ConstantNode.ZERO;
    }
}
