package io.luan.learn4j.structure.impl;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.MatMulNode;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * Matrix Multiplication, Support 2 sub expressions.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MatMul extends BaseExpression {

    public static final String TYPE = "MatMul";

    @Getter
    private Expression left;
    @Getter
    private Expression right;
    @Getter
    private boolean transposeLeft = false;
    @Getter
    private boolean transposeRight = false;

    public MatMul(String name, Expression left, Expression right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public MatMul(String name, Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
        super(name);
        this.left = left;
        this.right = right;
        this.transposeLeft = transposeLeft;
        this.transposeRight = transposeRight;
    }

    @Override
    public Expression getGradient(Expression target) {
        return null;
    }

    @Override
    public ExpressionType getType() {
        return null;
    }

}
