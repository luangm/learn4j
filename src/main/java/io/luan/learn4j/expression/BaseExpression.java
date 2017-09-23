package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseExpression implements Expression {

    @Getter
    private String name;

    private ComputeNode computeNode;

    BaseExpression(String name) {
        this.name = name;
    }

    public ComputeNode getComputeNode() {
        if (computeNode == null) {
            computeNode = createComputeNode();
        }
        return computeNode;
    }

    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getType() + "[" + this.getName() + "]@" + this.computeNode.toString();
    }

    abstract ComputeNode createComputeNode();
}
