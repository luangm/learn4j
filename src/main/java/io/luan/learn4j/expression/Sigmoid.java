package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Sigmoid {

    @Getter
    private Expression exp;

    public Sigmoid(String name, Expression exp) {
        this.exp = exp;
    }

    public Iterable<Expression> getDependencies() {
        return null;
    }

    public ComputeNode getComputeNode() {
        return null;
    }

    ComputeNode createComputeNode() {
        return null;
    }
}
