package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseExpression implements Expression {

    @Getter
    private String name;

    BaseExpression(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return this.getType() + "[" + this.getName() + "]";
    }

}
