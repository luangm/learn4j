package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;
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
    public void accept(Visitor visitor, Object... params) {
        visitor.visitAssign(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return target.getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        return target.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Assign;
    }

    protected Expression createGradient(Expression target) {
        return null;
    }
}
