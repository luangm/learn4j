package io.luan.learn4j.structure.factory;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.impl.binary.Add;
import io.luan.learn4j.structure.impl.binary.Multiply;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.transform.Negate;
import io.luan.learn4j.structure.impl.transform.Sign;
import io.luan.learn4j.structure.impl.transform.Sine;
import lombok.Getter;

/**
 * The ExpressionFactory class implements the Factory pattern for object creation.
 * <p>
 * The reason for a separate Factory class instead of constructors is simplification.
 * <p>
 * Whenever a node is created, it is simplified and the simplified node is returned.
 *
 * @author Guangmiao Luan
 * @since 17/09/2017.
 */
public class ExpressionFactory {

    @Getter
    private Graph graph;

    public ExpressionFactory(Graph graph) {
        this.graph = graph;
    }

    public static Expression createConstant(String name, Tensor value) {
//        return new Constant(name, value);
        return null;
    }

    public static Expression createDivide(String name, Expression left, Expression right) {
//        return new Divide(name, left, right);
        return null;
    }

    public static Expression createGroup(String name, Expression[] expList) {
//        return new Group(name, expList);
        return null;
    }

    public static Expression createMatMul(String name, Expression left, Expression right) {
//        return new MatMul(name, left, right);
        return null;
    }

    public static Expression createMatMul(String name, Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
//        return new MatMul(name, left, right, transposeLeft, transposeRight);
        return null;
    }

    public static Expression createMultiply(String name, Expression left, Expression right) {

        if (left == Constant.ONE) {
            return right;
        }
        if (left == Constant.ZERO) {
            return Constant.ZERO;
        }

        if (right == Constant.ONE) {
            return left;
        }
        if (right == Constant.ZERO) {
            return Constant.ZERO;
        }

//        return new Multiply(name, left, right);
        return null;
    }

    public static Expression createPower(String name, Expression base, Expression power) {
        //return new Power(name, base, power);
        return null;
    }

    public static Expression createReduceMean(String name, Expression base, int dimension) {
//        return new ReduceMean(name, base, dimension);
        return null;
    }

    public static Expression createSigmoid(String name, Expression base) {
//        return new Sigmoid(name, base);
        return null;
    }

    public static Expression createSigmoidGrad(String name, Expression base) {
//        return new SigmoidGrad(name, base);
        return null;
    }

    public static Expression createSign(String name, Expression base) {
//        return new Sign(name, base);
        return null;
    }

    public static Expression createStep(String name, Expression base) {
//        return new Step(name, base);
        return null;
    }

    public static Expression createSubtract(String name, Expression left, Expression right) {
        if (right == Constant.ZERO) {
            return left;
        }

//        return new Subtract(name, left, right);
        return null;
    }

    public Expression add(Expression left, Expression right, String name) {
        return graph.add(new Add(left, right, name));
    }

    public Expression createAssign(String name, Expression target, Expression newValue) {
//        return new Assign(name, target, newValue);
        return null;
    }

    public Expression multiply(Expression left, Expression right) {
        return multiply(left, right, null);
    }

    public Expression multiply(Expression left, Expression right, String name) {
        return graph.add(new Multiply(left, right, name));
    }

    public Expression neg(Expression base) {
        return neg(base, null);
    }

    public Expression neg(Expression base, String name) {
        return graph.add(new Negate(base, name));
    }

    public Expression negate(Expression base) {
        return negate(base, null);
    }

    public Expression negate(Expression base, String name) {
        return graph.add(new Negate(base, name));
    }

    public Expression reduceSum(Expression base, int dimension) {
        return reduceSum(base, dimension, null);
    }

    public Expression reduceSum(Expression base, int dimension, String name) {
        if (dimension >= 0) {
            return graph.add(new ReduceSum(base, dimension, name));
        }
        return base;
    }

    public Expression sign(Expression base) {
        return sign(base, null);
    }

    public Expression sign(Expression base, String name) {
        return graph.add(new Sign(base, name));
    }

    public Expression sin(Expression base) {
        return sin(base, null);
    }

    public Expression sin(Expression base, String name) {
        return graph.add(new Sine(base, name));
    }
}
