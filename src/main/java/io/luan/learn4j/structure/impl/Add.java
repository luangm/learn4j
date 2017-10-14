package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Add extends BinaryOp {

    public Add(String name, Expression left, Expression right) {
        super(name, left, right);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return getLeft().getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        return getLeft().getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Add;
    }
}
