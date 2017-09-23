package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import lombok.Getter;

/**
 * The Variable node is differentiable with Gradient Descend Algorithm
 * <p>
 * NOTE this is not the same as Variable in TensorFlow. This is equivalent to Placeholder in Tensorflow
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Variable extends BaseExpression {

    private static final String TYPE = "Variable";

    @Getter
    private int[] shape;

    public Variable(String name, int[] shape) {
        super(name);
        this.shape = shape;
    }

    public String getType() {
        return TYPE;
    }

    ComputeNode createComputeNode() {
        return null;
    }
}
