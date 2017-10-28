package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.utils.ShapeUtils;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract extends BinaryOp {

    // Stores the broadcasted shape
    private int[] _shape;

    public Subtract(String name, Expression left, Expression right) {
        super(name, left, right);

        this._shape = ShapeUtils.broadcastShapes(left.getShape(), right.getShape());
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitSubtract(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return getLeft().getRank();
    }

    @Override
    public int[] getShape() {
        return _shape;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Subtract;
    }

}
