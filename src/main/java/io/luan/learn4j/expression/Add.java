package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.AddNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Add extends BaseExpression {

    public static final String TYPE = "Add";

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    public Add(String name, Expression left, Expression right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitAdd(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return new AddNode(this.getName(), left.getComputeNode(), right.getComputeNode());
    }
}
