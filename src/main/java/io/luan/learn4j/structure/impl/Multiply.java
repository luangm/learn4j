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
public class Multiply extends BinaryOp {

    public Multiply(String name, Expression left, Expression right) {
        super(name, left, right);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return getLeft().getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        return getLeft().getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Multiply;
    }

    /**
     * d(AB) = dA * B + dB * A
     */
    protected Expression createGradient(Expression target) {
        Expression leftGrad = getLeft().getGradient(target);
        Expression rightGrad = getRight().getGradient(target);

        String gradName = this.getName() + "_" + target.getName();
        String part1Name = gradName + "$1";
        String part2Name = gradName + "$2";

        Expression part1 = ExpressionFactory.createMultiply(part1Name, leftGrad, getRight());
        Expression part2 = ExpressionFactory.createMultiply(part2Name, getLeft(), rightGrad);

        return ExpressionFactory.createAdd(gradName, part1, part2);
    }
}
