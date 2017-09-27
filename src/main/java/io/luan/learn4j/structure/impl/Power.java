package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Power extends BaseExpression {

    @Getter
    private Expression base;

    @Getter
    private Expression power;

    public Power(String name, Expression base, Expression power) {
        super(name);
        this.base = base;
        this.power = power;
    }

    @Override
    public Expression getGradient(Expression target) {
        Expression baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target;

        String subName = gradName + "$sub";
        Expression sub = ExpressionFactory.createSubtract(subName, power, Constant.ONE);

        String newPowerName = gradName + "$power";
        Expression newPower = ExpressionFactory.createPower(newPowerName, base, sub);

        String newMultiplyName = gradName + "$mul";
        Expression mul = ExpressionFactory.createMultiply(newMultiplyName, sub, newPower);

        return ExpressionFactory.createMultiply(gradName, mul, baseGrad);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Power;
    }
}
