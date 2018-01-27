package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * A Group Expression groups multiple Expression together
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Group extends BaseExpression {

    @Getter
    private Expression[] list;

    public Group(Expression[] list, String name) {
        super(name);
        this.list = list;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitGroup(this, params);
    }

    @Override
    public int[] getShape() {
        return list[0].getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Group;
    }

}
