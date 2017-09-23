package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class SubtractNode extends BaseNode {

    @Getter
    private ComputeNode left;

    @Getter
    private ComputeNode right;

    public SubtractNode(String name, ComputeNode left, ComputeNode right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    ComputeNode createGradientNode(String target) {
        ComputeNode leftGrad = left.getGradient(target);
        ComputeNode rightGrad = right.getGradient(target);

        String gradName = this.getName() + "_" + target;

        return NodeFactory.createSubtractNode(gradName, leftGrad, rightGrad);
    }
}
