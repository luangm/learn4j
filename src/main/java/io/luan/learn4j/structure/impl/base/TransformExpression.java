package io.luan.learn4j.structure.impl.base;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public abstract class TransformExpression extends BaseExpression {

    @Getter
    private Expression base;

    public TransformExpression(Expression base, String name) {
        super(name);
        this.base = base;
    }

    @Override
    public int[] getShape() {
        return base.getShape();
    }
}
