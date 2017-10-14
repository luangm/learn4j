package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.impl.Add;
import io.luan.learn4j.structure.impl.Constant;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 06/10/2017.
 */
public class ReverseGradientVisitor extends BaseVisitor {

    @Getter
    private Expression source;

    @Getter
    private Expression target;

    public ReverseGradientVisitor() {

    }

    @Override
    protected void visitAdd(Add node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        Expression leftGrad = grad;
        Expression rightGrad = grad;

        node.getLeft().setGradient(node, leftGrad);
        node.getRight().setGradient(node, rightGrad);

        node.getLeft().accept(this);
        node.getRight().accept(this);
    }



    private static Expression getGradientOrDefault(Expression node, Object... params) {
        if (params.length > 0) {
            return (Expression) params[0];
        }

        return new Constant("FILL", Tensor.scalars(1, node.getShape()[0], node.getShape()[1]));
    }

}
