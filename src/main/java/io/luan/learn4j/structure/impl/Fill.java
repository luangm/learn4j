package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Fill extends BaseExpression {

    @Getter
    private Number scalar;

    @Getter
    private int[] shape;

    public Fill(String name, Number scalar, int[] shape) {
        super(name);
        this.scalar = scalar;
        this.shape = shape;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitFill(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return shape.length;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Fill;
    }
}
