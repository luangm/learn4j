package io.luan.learn4j.structure.factory;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.impl.*;

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

    public static Expression createAdd(String name, Expression left, Expression right) {
        if (left == Constant.ZERO) {
            return right;
        }

        if (right == Constant.ZERO) {
            return left;
        }

        return new Add(name, left, right);
    }

    public static Expression createAssign(String name, Expression target, Expression newValue) {
        return new Assign(name, target, newValue);
    }

    public static Expression createConstant(String name, Tensor value) {
        return new Constant(name, value);
    }

    public static Expression createGroup(String name, Expression[] expList) {
        return new Group(name, expList);
    }

    public static Expression createMatMul(String name, Expression left, Expression right) {
        return new MatMul(name, left, right);
    }

    public static Expression createMatMul(String name, Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
        return new MatMul(name, left, right, transposeLeft, transposeRight);
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

        return new Multiply(name, left, right);
    }

    public static Expression createNegate(String name, Expression base) {
        return new Negate(name, base);
    }

    public static Expression createPower(String name, Expression base, Expression power) {
        return new Power(name, base, power);
    }

    public static Expression createReduceMean(String name, Expression base, int dimension) {
        return new ReduceMean(name, base, dimension);
    }

    public static Expression createReduceSum(String name, Expression base, int dimension) {
        return new ReduceSum(name, base, dimension);
    }

    public static Expression createSigmoid(String name, Expression base) {
        return new Sigmoid(name, base);
    }

    public static Expression createSigmoidGrad(String name, Expression base) {
        return new SigmoidGrad(name, base);
    }

    public static Expression createSubtract(String name, Expression left, Expression right) {
        if (right == Constant.ZERO) {
            return left;
        }

        return new Subtract(name, left, right);
    }
}
