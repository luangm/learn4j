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

    ComputeNode createGradientNode(String nodeName) {
        if (this.getName().equals(nodeName)) {
            // self
            return ConstantNode.IDENTITY;
        }
        return ConstantNode.ZERO;
    }
}
