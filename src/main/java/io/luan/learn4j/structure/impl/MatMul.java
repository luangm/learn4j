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

    private int[] _shape;

    public MatMul(String name, Expression left, Expression right) {
        this(name, left, right, false, false);
    }

    public MatMul(String name, Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
        super(name, left, right);
        this.transposeLeft = transposeLeft;
        this.transposeRight = transposeRight;

        _shape = new int[2];
        _shape[0] = transposeLeft ? left.getShape()[1] : left.getShape()[0];
        _shape[1] = transposeRight ? right.getShape()[0] : right.getShape()[1];
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
        return _shape;
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
