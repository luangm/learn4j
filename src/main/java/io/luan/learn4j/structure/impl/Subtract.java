package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract extends BinaryOp {

    public Subtract(String name, Expression left, Expression right) {
        super(name, left, right);
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitSubtract(this, params);
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
        return ExpressionType.Subtract;
    }

    protected Expression createGradient(Expression target) {
        Expression leftGrad = getLeft().getGradient(target);
        Expression rightGrad = getRight().getGradient(target);

        String gradName = this.getName() + "_" + target.getName();

        return ExpressionFactory.createSubtract(gradName, leftGrad, rightGrad);
    }
}
