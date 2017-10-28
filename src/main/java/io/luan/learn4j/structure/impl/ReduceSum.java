package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Reduce Sum
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ReduceSum extends BaseExpression {

    @Getter
    private Expression base;

    @Getter
    private int dimension;

    private int[] _shape;

    public ReduceSum(String name, Expression base, int dimension) {
        super(name);
        this.base = base;
        this.dimension = dimension;
        this._shape = base.getShape().clone();
        if (dimension >= 0) {
            this._shape[dimension] = 1;
        } else {
            for (int i = 0; i < _shape.length; i++) {
                _shape[i] = 1;
            }
        }
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitReduceSum(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return base.getRank();
    }

    @Override
    public int[] getShape() {
        return _shape;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.ReduceSum;
    }
}
