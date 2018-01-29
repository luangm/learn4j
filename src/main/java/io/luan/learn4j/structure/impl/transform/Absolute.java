package io.luan.learn4j.structure.impl.transform;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.TransformExpression;
import io.luan.learn4j.visitor.Visitor;

/**
 * Absolute function abs(x)
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Absolute extends TransformExpression {

    public Absolute(Expression base, String name) {
        super(base, name);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitAbsolute(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Absolute;
    }

}
