package io.luan.learn4j.compute;

import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class AddNode extends BaseNode {

    @Getter
    private ComputeNode left;

    @Getter
    private ComputeNode right;

    public AddNode(String name, ComputeNode left, ComputeNode right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    ComputeNode createGradientNode(String nodeName) {
        ComputeNode leftGrad = left.getGradient(nodeName);
        ComputeNode rightGrad = right.getGradient(nodeName);

        String gradName = this.getName() + "_" + nodeName;

        return new AddNode(gradName, leftGrad, rightGrad);
    }
}
