package io.luan.learn4j.compute.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ReduceMeanNode extends BaseNode {

    @Getter
    private ComputeNode base;

    @Getter
    private int dimension;

    public ReduceMeanNode(String name, ComputeNode base, int dimension) {
        super(name);
        this.base = base;
        this.dimension = dimension;
    }

    @Override
    ComputeNode createGradientNode(String target) {
        ComputeNode baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target;

        return NodeFactory.createReduceMeanNode(gradName, baseGrad, this.dimension);
    }
}
