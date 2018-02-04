package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public abstract class BinaryExpression extends BaseExpression {

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    public BinaryExpression(Expression left, Expression right, String name) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isInvalid() {
        return super.isInvalid() || left.isInvalid() || right.isInvalid();
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + left.getId();
        hash = hash * 31 + right.getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        BinaryExpression other = (BinaryExpression) obj;
        return this.getLeft().getId() == other.getLeft().getId()
                && this.getRight().getId() == other.getRight().getId();
    }
}
