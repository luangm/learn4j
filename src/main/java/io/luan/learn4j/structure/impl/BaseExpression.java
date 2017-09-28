package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public abstract class BaseExpression implements Expression {

    private Map<Expression, Expression> gradientMap = new HashMap<>();

    @Getter
    @Setter
    private String name;

    protected BaseExpression(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Expression getGradient(Expression target) {
        Expression grad = gradientMap.get(target);
        if (grad == null) {
            grad = createGradient(target);
            gradientMap.put(target, grad);
        }
        return grad;
    }

    @Override
    public String toString() {
        return this.getType() + "[" + this.getName() + "]";
    }

    protected abstract Expression createGradient(Expression target);

}
