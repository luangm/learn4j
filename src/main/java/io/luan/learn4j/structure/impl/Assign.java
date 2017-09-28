package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Assign extends BaseExpression {

    @Getter
    private Expression target;

    @Getter
    private Expression newValue;

    public Assign(String name, Expression target, Expression newValue) {
        super(name);
        this.target = target;
        this.newValue = newValue;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Assign;
    }

    protected Expression createGradient(Expression target) {
        return null;
    }
}
