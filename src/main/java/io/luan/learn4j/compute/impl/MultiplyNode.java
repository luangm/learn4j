package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
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
    ComputeNode createGradientNode(String target) {
        ComputeNode leftGrad = left.getGradient(target);
        ComputeNode rightGrad = right.getGradient(target);

        String gradName = this.getName() + "_" + target;
        String part1Name = gradName + "$1";
        String part2Name = gradName + "$2";

        ComputeNode part1 = NodeFactory.createMultiplyNode(part1Name, leftGrad, right);
        ComputeNode part2 = NodeFactory.createMultiplyNode(part2Name, left, rightGrad);

        return NodeFactory.createAddNode(gradName, part1, part2);
    }
}
