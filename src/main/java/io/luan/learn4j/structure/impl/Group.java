package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import lombok.Getter;

/**
 * A Group Expression groups multiple Expression together
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Group extends BaseExpression {

    @Getter
    private Expression[] expList;

    public Group(String name, Expression[] expList) {
        super(name);
        this.expList = expList;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Group;
    }

    protected Expression createGradient(Expression target) {
        Expression[] gradList = new Expression[expList.length];

        for (int i = 0; i < expList.length; i++) {
            gradList[i] = expList[i].getGradient(target);
        }

        String gradName = this.getName() + "_" + target.getName();

        return ExpressionFactory.createGroup(gradName, gradList);
    }
}
