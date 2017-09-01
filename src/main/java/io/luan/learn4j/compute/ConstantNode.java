package io.luan.learn4j.compute;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ConstantNode extends BaseNode {

    private final static List<ComputeNode> EMPTY = new ArrayList<ComputeNode>();

    public ConstantNode(INDArray value) {
        super(value);
    }

    public static ConstantNode ones(int row, int col) {
        INDArray array = Nd4j.ones(row, col);
        return new ConstantNode(array);
    }

    public List<ComputeNode> getDependencies() {
        return EMPTY;
    }

    public ComputeNode getGradient(ParameterNode node) {
        return null;
    }
}
