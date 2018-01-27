package io.luan.learn4j.structure.impl.binary;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BinaryExpression;
import io.luan.learn4j.utils.ShapeUtils;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Element-wise Divide
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Divide extends BinaryExpression {

    @Getter
    private int[] shape;

    public Divide(Expression left, Expression right, String name) {
        super(left, right, name);
        this.shape = ShapeUtils.broadcastShapes(left.getShape(), right.getShape());
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitDivide(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Divide;
    }

}
