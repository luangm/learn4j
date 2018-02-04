package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public abstract class TransformExpression extends BaseExpression {

    @Getter
    private Expression base;

    public TransformExpression(Expression base, String name) {
        super(name);
        this.base = base;
    }

    @Override
    public int[] getShape() {
        return base.getShape();
    }

    @Override
    public boolean isInvalid() {
        return super.isInvalid() || base.isInvalid();
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + base.getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        TransformExpression other = (TransformExpression) obj;
        return this.getBase().getId() == other.getBase().getId();
    }
}
