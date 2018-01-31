package io.luan.learn4j.structure.impl.binary;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BinaryExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Matrix Multiplication, Support 2 sub expressions.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MatMul extends BinaryExpression {

    @Getter
    private int[] shape;

    @Getter
    private boolean transposeLeft;

    @Getter
    private boolean transposeRight;

    public MatMul(Expression left, Expression right, boolean transposeLeft, boolean transposeRight, String name) {
        super(left, right, name);
        this.transposeLeft = transposeLeft;
        this.transposeRight = transposeRight;

        this.shape = new int[2];
        this.shape[0] = transposeLeft ? left.getShape()[1] : left.getShape()[0];
        this.shape[1] = transposeRight ? right.getShape()[0] : right.getShape()[1];
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitMatMul(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.MatMul;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + (transposeLeft ? 1 : 0);
        hash = hash * 31 + (transposeRight ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        MatMul other = (MatMul) obj;
        return this.isTransposeLeft() == other.isTransposeLeft()
                && this.isTransposeRight() == other.isTransposeRight();
    }
}
