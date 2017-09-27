package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
abstract class UnaryOp extends BaseExpression {

    @Getter
    private Expression base;


    UnaryOp(String name, Expression base) {
        super(name);
        this.base = base;
    }
}
