package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * A Constant expression is an Expression wrapping a Tensor value, where the value will not change.
 *
 * @author Guangmiao Luan
 * @since 24/09/2017.
 */
public class Constant extends BaseExpression {

    public static final Constant ZERO = new Constant("ZERO", Tensor.scalar(0));
    public static final Constant ONE = new Constant("ONE", Tensor.scalar(1));
    public static final Constant TWO = new Constant("TWO", Tensor.scalar(2));

    @Getter
    private Tensor value;

    public Constant(String name, Tensor value) {
        super(name);
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitConstant(this, params);
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
        return ExpressionType.Constant;
    }
}
