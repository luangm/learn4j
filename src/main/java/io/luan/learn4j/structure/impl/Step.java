package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;

/**
 * A Step operation returns 1 if base is > 0, or 0 if <= 0
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Step extends UnaryOp {

    public Step(String name, Expression base) {
        super(name, base);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitStep(this, params);
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
        return ExpressionType.Step;
    }
}
