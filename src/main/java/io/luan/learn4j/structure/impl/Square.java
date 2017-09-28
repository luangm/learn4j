package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Square extends UnaryOp {

    public Square(String name, Expression base) {
        super(name, base);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Square;
    }

    protected Expression createGradient(Expression target) {
        Expression baseGrad = getBase().getGradient(target);
        String gradName = this.getName() + "_" + target.getName();

        String mulName = gradName + "$mul";
        Expression mul = ExpressionFactory.createMultiply(mulName, getBase(), baseGrad);

        Constant two = Constant.TWO;

        return ExpressionFactory.createMultiply(gradName, two, mul);
    }
}
