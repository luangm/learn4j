package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract extends BinaryOp {

    public Subtract(String name, Expression left, Expression right) {
        super(name, left, right);
    }

    @Override
    public Expression getGradient(Expression target) {
        Expression leftGrad = getLeft().getGradient(target);
        Expression rightGrad = getRight().getGradient(target);

        String gradName = this.getName() + "_" + target.getName();

        return ExpressionFactory.createSubtract(gradName, leftGrad, rightGrad);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Subtract;
    }
}
