package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.ReduceMeanNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ReduceMean extends BaseExpression {

    public static final String TYPE = "ReduceMean";

    @Getter
    private Expression base;

    @Getter
    private int dimension;

    public ReduceMean(String name, Expression base, int dimension) {
        super(name);
        this.base = base;
        this.dimension = dimension;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitReduceMean(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return new ReduceMeanNode(this.getName(), base.getComputeNode(), dimension);
    }
}
