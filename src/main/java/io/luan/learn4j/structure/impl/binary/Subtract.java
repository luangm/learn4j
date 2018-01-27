package io.luan.learn4j.structure.impl.binary;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BinaryExpression;
import io.luan.learn4j.utils.ShapeUtils;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * left - right
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract extends BinaryExpression {

    @Getter
    private int[] shape;

    public Subtract(Expression left, Expression right, String name) {
        super(left, right, name);
        this.shape = ShapeUtils.broadcastShapes(left.getShape(), right.getShape());
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitSubtract(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Subtract;
    }

}
