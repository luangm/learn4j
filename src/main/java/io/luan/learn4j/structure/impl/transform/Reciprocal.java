package io.luan.learn4j.structure.impl.transform;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.TransformExpression;
import io.luan.learn4j.visitor.Visitor;

/**
 * 1/x
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Reciprocal extends TransformExpression {

    public Reciprocal(Expression base, String name) {
        super(base, name);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitReciprocal(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Reciprocal;
    }

}
