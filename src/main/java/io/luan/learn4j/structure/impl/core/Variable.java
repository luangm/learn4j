package io.luan.learn4j.structure.impl.core;

import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
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

    public Variable(int[] shape, String name) {
        super(name);
        this.shape = shape;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitVariable(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Variable;
    }

}
