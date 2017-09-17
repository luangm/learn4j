package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.MultiplyNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Multiply extends BaseExpression {

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    public Multiply(String name, Expression left, Expression right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitMultiply(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return new MultiplyNode(this.getName(), left.getComputeNode(), right.getComputeNode());
    }
}
