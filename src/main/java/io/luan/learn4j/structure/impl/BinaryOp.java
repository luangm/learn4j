package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
abstract class BinaryOp extends BaseExpression {

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    BinaryOp(String name, Expression left, Expression right) {
        super(name);
        this.left = left;
        this.right = right;
    }
}
