package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class PowerNode extends BaseNode {

    @Getter
    private ComputeNode base;

    @Getter
    private ComputeNode power;

    public PowerNode(String name, ComputeNode base, ComputeNode power) {
        super(name);
        this.base = base;
        this.power = power;
    }

    @Override
    ComputeNode createGradientNode(String target) {
        ComputeNode baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target;

        String subName = gradName + "$sub";
        ComputeNode sub = NodeFactory.createSubtractNode(subName, power, ConstantNode.ONE);

        String newPowerName = gradName + "$power";
        ComputeNode newPower = NodeFactory.createPowerNode(newPowerName, base, sub);

        String newMultiplyName = gradName + "$mul";
        ComputeNode mul = NodeFactory.createMultiplyNode(newMultiplyName, sub, newPower);

        return NodeFactory.createMultiplyNode(gradName, mul, baseGrad);
    }
}
