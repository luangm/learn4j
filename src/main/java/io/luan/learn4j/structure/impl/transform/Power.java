package io.luan.learn4j.structure.impl.transform;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.TransformExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Power extends TransformExpression {

    // TODO: Change to Integer?
    @Getter
    private Expression power;

    public Power(Expression base, Expression power, String name) {
        super(base, name);
        this.power = power;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitPower(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Power;
    }

}
