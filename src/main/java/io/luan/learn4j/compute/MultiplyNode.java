package io.luan.learn4j.compute;

import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class MultiplyNode extends BaseNode {

    @Getter
    private ComputeNode left;

    @Getter
    private ComputeNode right;

    public MultiplyNode(String name, ComputeNode left, ComputeNode right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    /**
     * d(AB) = dA * B + dB * A
     */
    @Override
    ComputeNode createGradientNode(String nodeName) {
        ComputeNode leftGrad = left.getGradient(nodeName);
        ComputeNode rightGrad = right.getGradient(nodeName);

        String gradName = this.getName() + "_" + nodeName;
        String part1Name = gradName + "$1";
        String part2Name = gradName + "$1";

        ComputeNode part1 = new MultiplyNode(part1Name, leftGrad, right);
        ComputeNode part2 = new MultiplyNode(part2Name, left, rightGrad);

        return new AddNode(gradName, part1, part2);
    }
}
