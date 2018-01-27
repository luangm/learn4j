package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.utils.ShapeUtils;
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

}
