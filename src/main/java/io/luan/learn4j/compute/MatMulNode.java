package io.luan.learn4j.compute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class MatMulNode extends BaseNode {

    private ComputeNode leftNode;
    private ComputeNode rightNode;

    private List<ComputeNode> nodes;

    public MatMulNode(ComputeNode leftNode, ComputeNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        nodes = new ArrayList<ComputeNode>();
        nodes.add(leftNode);
        nodes.add(rightNode);
    }

    public ComputeNode getLeft() {
        return leftNode;
    }

    public ComputeNode getRight() {
        return rightNode;
    }

    public List<ComputeNode> getDependencies() {
        return nodes;
    }

    public ComputeNode getGradient(ParameterNode node) {
        ComputeNode leftGrad = leftNode.getGradient(node);
        ComputeNode rightGrad = rightNode.getGradient(node);

        if (leftGrad != null && rightGrad != null) {
            return new MatMulNode(leftGrad, rightGrad);
        }

        return leftGrad == null ? rightGrad : leftGrad;
    }
}
