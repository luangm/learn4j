package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Matrix Multiplication, Support 2 sub expressions.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MatMul extends BinaryOp {

    @Getter
    private boolean transposeLeft = false;

    @Getter
    private boolean transposeRight = false;

    public MatMul(String name, Expression left, Expression right) {
        super(name, left, right);
    }

    public MatMul(String name, Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
        super(name, left, right);
        this.transposeLeft = transposeLeft;
        this.transposeRight = transposeRight;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitMatMul(this, params);
    }

    @Override
    public int getRank() {
        // TODO: Should check for broadcast rules
        return getLeft().getRank();
    }

    @Override
    public int[] getShape() {
        // TODO: Should check for broadcast rules
        int[] shape = new int[2];
        shape[0] = getLeft().getShape()[0];
        shape[1] = getRight().getShape()[1];
        return shape;
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.MatMul;
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

        Expression part1 = ExpressionFactory.createMatMul(part1Name, leftGrad, getRight());
        Expression part2 = ExpressionFactory.createMatMul(part2Name, getLeft(), rightGrad);

        return ExpressionFactory.createAdd(gradName, part1, part2);
    }

}
