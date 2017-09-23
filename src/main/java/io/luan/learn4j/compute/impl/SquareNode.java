package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import lombok.Getter;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class SquareNode extends BaseNode {

    @Getter
    private ComputeNode base;

    public SquareNode(String name, ComputeNode base) {
        super(name);
        this.base = base;
    }

    @Override
    ComputeNode createGradientNode(String target) {
        ComputeNode baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target;

        String newMultiplyName = gradName + "$mul";
        ComputeNode mul = NodeFactory.createMultiplyNode(newMultiplyName, this.base, baseGrad);

        ConstantNode two = new ConstantNode("TWO", Nd4j.ones(1, 1).add(1));

        return NodeFactory.createMultiplyNode(gradName, two, mul);
    }
}
