package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public abstract class BinaryExpression extends BaseExpression {

    @Getter
    private Expression left;

    @Getter
    private Expression right;

    public BinaryExpression(Expression left, Expression right, String name) {
        super(name);
        this.left = left;
        this.right = right;
    }

}
