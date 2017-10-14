package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Negate extends UnaryOp {

    public Negate(String name, Expression base) {
        super(name, base);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitNegate(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return getBase().getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        return getBase().getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Negate;
    }

}
