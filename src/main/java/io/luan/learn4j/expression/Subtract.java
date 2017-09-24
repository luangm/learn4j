package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract extends BaseExpression {

    public static final String TYPE = "Subtract";

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    public Subtract(String name, Expression left, Expression right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitSubtract(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return NodeFactory.createSubtractNode(this.getName(), left.getComputeNode(), right.getComputeNode());
    }
}
