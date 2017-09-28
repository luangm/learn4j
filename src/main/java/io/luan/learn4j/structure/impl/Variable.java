package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
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

    @Getter
    private int[] shape;

    public Variable(String name, int[] shape) {
        super(name);
        this.shape = shape;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Variable;
    }

    protected Expression createGradient(Expression target) {
        if (target == this) {
            return Constant.ONE;
        }
        return Constant.ZERO;
    }
}
