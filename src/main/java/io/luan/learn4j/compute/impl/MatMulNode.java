package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class MatMulNode extends BaseNode {

    @Getter
    private ComputeNode left;

    @Getter
    private ComputeNode right;

    @Getter
    private boolean transposeLeft;

    @Getter
    private boolean transposeRight;

    public MatMulNode(String name, ComputeNode left, ComputeNode right, boolean transposeLeft, boolean transposeRight) {
        super(name);
        this.left = left;
        this.right = right;
        this.transposeLeft = transposeLeft;
        this.transposeRight = transposeRight;
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

        ComputeNode part1 = new MatMulNode(part1Name, leftGrad, right, false, true);
        ComputeNode part2 = new MatMulNode(part2Name, left, rightGrad, true, false);

        return new AddNode(gradName, part1, part2);
    }
}
