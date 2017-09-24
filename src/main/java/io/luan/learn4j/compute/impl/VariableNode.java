package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class VariableNode extends BaseNode {

    public VariableNode(String name, int[] shape) {
        super(name);
    }

    ComputeNode createGradientNode(String target) {
        if (this.getName().equals(target)) {
            // self
            return ConstantNode.IDENTITY;
        }
        return ConstantNode.ZERO;
    }
}
