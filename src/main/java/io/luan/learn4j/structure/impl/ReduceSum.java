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

    public ReduceSum(String name, Expression base, int dimension) {
        super(name);
        this.base = base;
        this.dimension = dimension;
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
        // TODO: Should check for broadcast rules
        return base.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.ReduceSum;
    }
}
