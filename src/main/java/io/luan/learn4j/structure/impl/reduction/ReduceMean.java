package io.luan.learn4j.structure.impl.reduction;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.ReductionExpression;
import io.luan.learn4j.visitor.Visitor;

/**
 * Reduce Mean
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ReduceMean extends ReductionExpression {

    public ReduceMean(Expression base, int dimension, String name) {
        super(base, dimension, name);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitReduceMean(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.ReduceMean;
    }

}
