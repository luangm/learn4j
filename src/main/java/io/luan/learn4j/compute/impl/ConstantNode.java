package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ConstantNode extends BaseNode {

    public static final ConstantNode ZERO = new ConstantNode("ZERO", Nd4j.zeros(1));
    public static final ConstantNode ONE = new ConstantNode("ONE", Nd4j.ones(1));
    public static final ConstantNode IDENTITY = new ConstantNode("IDENTITY", Nd4j.eye(1));

    public ConstantNode(String name, INDArray value) {
        super(name, value);
    }

    public static ConstantNode ones(String name, int row, int col) {
        INDArray array = Nd4j.ones(row, col);
        return new ConstantNode(name, array);
    }

    public static ComputeNode identity(String name, int i) {
        INDArray array = Nd4j.eye(i);
        return new ConstantNode(name, array);
    }

    public static ConstantNode zeros(String name, int row, int col) {
        INDArray array = Nd4j.zeros(row, col);
        return new ConstantNode(name, array);
    }

    @Override
    ComputeNode createGradientNode(String target) {
        return ZERO;
    }
}
