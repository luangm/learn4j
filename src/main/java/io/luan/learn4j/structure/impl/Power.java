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
    public int getRank() {
        // TODO: Should check for broadcast rules
        return base.getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        return base.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Power;
    }

    protected Expression createGradient(Expression target) {
        Expression baseGrad = base.getGradient(target);
        String gradName = this.getName() + "_" + target.getName();

        String subName = gradName + "$sub";
        Expression sub = ExpressionFactory.createSubtract(subName, power, Constant.ONE);

        String newPowerName = gradName + "$power";
        Expression newPower = ExpressionFactory.createPower(newPowerName, base, sub);

        String newMultiplyName = gradName + "$mul";
        Expression mul = ExpressionFactory.createMultiply(newMultiplyName, sub, newPower);

        return ExpressionFactory.createMultiply(gradName, mul, baseGrad);
    }
}
