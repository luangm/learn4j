package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import lombok.Getter;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ReduceMean extends BaseExpression {

    @Getter
    private Expression base;

    @Getter
    private int dimension;

    public ReduceMean(String name, Expression base, int dimension) {
        super(name);
        this.base = base;
        this.dimension = dimension;
    }

    @Override
    public Expression getGradient(Expression target) {
        Expression baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target;

        return ExpressionFactory.createReduceMean(gradName, baseGrad, this.dimension);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.ReduceMean;
    }
}
