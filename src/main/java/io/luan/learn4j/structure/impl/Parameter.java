package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;
import lombok.Setter;

/**
 * Parameter is holder for parameters to be updated on each gradient descend run
 * <p>
 * This is equivalent to TensorFlow.Variable
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Parameter extends BaseExpression {

    @Getter
    @Setter
    private Tensor value;

    public Parameter(String name, Tensor value) {
        super(name);
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitParameter(this, params);
    }

    @Override
    public int getRank() {
        return value.getRank();
    }

    @Override
    public int[] getShape() {
        return value.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Parameter;
    }

    protected Expression createGradient(Expression target) {
        if (target == this) {
            return Constant.ONE;
        }
        return Constant.ZERO;
    }

}
