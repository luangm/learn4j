package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.factory.NodeFactory;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Power extends BaseExpression {

    private static final String TYPE = "Power";

    @Getter
    private Expression base;

    @Getter
    private Expression power;

    public Power(String name, Expression base, Expression power) {
        super(name);
        this.base = base;
        this.power = power;
    }

    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visitPower(this);
    }

    @Override
    ComputeNode createComputeNode() {
        return NodeFactory.createPowerNode(getName(), base.getComputeNode(), power.getComputeNode());
    }
}
