package io.luan.learn4j.structure.impl.core;

import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;
import lombok.Setter;

/**
 * Parameter is holder for parameters to be updated on each gradient descend eval
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

    public Parameter(Tensor value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitParameter(this, params);
    }

    @Override
    public int[] getShape() {
        return value.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Parameter;
    }

}
