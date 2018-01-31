package io.luan.learn4j.structure.impl.cnn;

import io.luan.learn4j.core.utils.ShapeUtils;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.structure.impl.base.BinaryExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;
import org.nd4j.linalg.convolution.Convolution;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Scalar Multiply
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Conv2d extends BaseExpression {

    @Getter
    private Expression image;

    @Getter
    private Expression kernel;

    @Getter
    private int[] shape;

    public Conv2d(Expression image, Expression kernel, String name) {
        super(name);
        this.image = image;
        this.kernel = kernel;
//        this.shape = ShapeUtils.broadcastShapes(left.getShape(), right.getShape());
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
//        visitor.visit(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Conv2d;
    }

}
