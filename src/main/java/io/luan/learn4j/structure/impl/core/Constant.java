package io.luan.learn4j.structure.impl.core;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * A Constant expression is an Expression wrapping a Tensor value, where the value will not change.
 *
 * @author Guangmiao Luan
 * @since 24/09/2017.
 */
public class Constant extends BaseExpression {

    public static final Constant ZERO = new Constant(Tensor.scalar(0), "ZERO");
    public static final Constant ONE = new Constant(Tensor.scalar(1), "ONE");
    public static final Constant TWO = new Constant(Tensor.scalar(2), "TWO");

    @Getter
    private Tensor value;

    public Constant(Tensor value, String name) {
        super(name);
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitConstant(this, params);
    }

    @Override
    public int[] getShape() {
        return value.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Constant;
    }

    @Override
    public void setValue(Tensor value) {
        throw new UnsupportedOperationException("Constant's value cannot be set after creation");
    }
}
