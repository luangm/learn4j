package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Fill extends BaseExpression {

    @Getter
    private Number scalar;

    @Getter
    private int[] shape;

    public Fill(Number scalar, int[] shape, String name) {
        super(name);
        this.scalar = scalar;
        this.shape = shape;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitFill(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Fill;
    }

}
