package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.SquareNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Square extends BaseExpression {

    public static final String TYPE = "Square";

    @Getter
    private Expression base;

    public Square(String name, Expression base) {
        super(name);
        this.base = base;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitSquare(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return new SquareNode(this.getName(), base.getComputeNode());
    }
}
