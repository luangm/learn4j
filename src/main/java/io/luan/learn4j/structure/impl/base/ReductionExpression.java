package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.core.utils.ShapeUtils;
import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * Reduction Expression performs dimensionality reduction along the given dimension.
 * If dimension == -1 then this applies to all dimensions
 *
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public abstract class ReductionExpression extends BaseExpression {

    @Getter
    private Expression base;

    @Getter
    private int dimension;

    @Getter
    private int[] shape;

    public ReductionExpression(Expression base, int dimension, String name) {
        super(name);
        this.base = base;
        this.dimension = dimension;
        this.shape = ShapeUtils.reduce(base.getShape(), dimension);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + base.getId();
        hash = hash * 31 + dimension;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        ReductionExpression other = (ReductionExpression) obj;
        return this.getBase().getId() == other.getBase().getId()
                && this.dimension == other.dimension;
    }

}
