package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Abs extends UnaryOp {

    public Abs(String name, Expression base) {
        super(name, base);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitAbs(this, params);
    }

    @Override
    public int getRank() {
        return getBase().getRank();
    }

    @Override
    public int[] getShape() {
        return getBase().getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Abs;
    }

}
