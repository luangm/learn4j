package io.luan.learn4j.structure.impl.transform;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.TransformExpression;
import io.luan.learn4j.visitor.Visitor;

/**
 * Negation (-x)
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Negate extends TransformExpression {

    public Negate(Expression base, String name) {
        super(base, name);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitNegate(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Negate;
    }

}
