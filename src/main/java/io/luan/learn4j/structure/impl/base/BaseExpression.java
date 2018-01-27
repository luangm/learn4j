package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A base implementation of the Expression interface.
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public abstract class BaseExpression implements Expression {

    private static AtomicInteger ID_COUNTER = new AtomicInteger(1);

    private Map<Expression, Expression> gradientMap = new HashMap<>();

    @Getter
    private int id;

    @Getter
    private String name;

    protected BaseExpression(String name) {
        this.name = name;
        this.id = ID_COUNTER.getAndIncrement();
    }

    @Override
    public Expression getGradient(Expression target) {
        return gradientMap.get(target);
    }

    public Expression getGradient() {
        return gradientMap.values().stream().findFirst().get();
    }

    @Override
    public void setGradient(Expression target, Expression gradient) {
        gradientMap.put(target, gradient);
    }

    @Override
    public String toString() {
        return this.getType() + "[" + this.getId() + "]";
    }

}
