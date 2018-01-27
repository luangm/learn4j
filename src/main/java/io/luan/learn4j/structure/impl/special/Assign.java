package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * TODO: Remove this. Replace with Expression.value = xxx
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Assign extends BaseExpression {

    @Getter
    private Expression target;

    @Getter
    private Expression newValue;

    public Assign(Expression target, Expression newValue, String name) {
        super(name);
        this.target = target;
        this.newValue = newValue;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitAssign(this, params);
    }

    @Override
    public int[] getShape() {
        return target.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Assign;
    }

}
