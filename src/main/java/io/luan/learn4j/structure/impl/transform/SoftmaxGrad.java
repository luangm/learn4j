package io.luan.learn4j.structure.impl.transform;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.TransformExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class SoftmaxGrad extends TransformExpression {

    @Getter
    private Expression grad;

    public SoftmaxGrad(Expression base, Expression grad, String name) {
        super(base, name);
        this.grad = grad;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitSoftmaxGrad(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.SoftmaxGrad;
    }

}
