package io.luan.learn4j.structure.factory;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.Assign;
import io.luan.learn4j.structure.impl.special.Group;
import io.luan.learn4j.structure.impl.special.MultiAssign;
import io.luan.learn4j.structure.impl.special.Tile;
import io.luan.learn4j.structure.impl.transform.*;
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

    public Expression abs(Expression base, String name) {
        Expression result = graph.add(new Absolute(base, name));
        base.addObserver(result);
        return result;
    }

    public Expression add(Expression left, Expression right, String name) {
        Expression result = graph.add(new Add(left, right, name));
        left.addObserver(result);
        right.addObserver(result);
        return result;
    }

    public Expression assign(Expression target, Expression value) {
        return assign(target, value, null);
    }

    public Expression assign(Expression target, Expression value, String name) {
        return graph.add(new Assign(target, value, name));
    }

    public Expression constant(Number number) {
        return constant(number, null);
    }

    public Expression constant(Number number, String name) {
        return graph.add(new Constant(Tensor.scalar(number), name));
    }

    public Expression cos(Expression base) {
        return cos(base, null);
    }

    public Expression cos(Expression base, String name) {
        return graph.add(new Cosine(base, name));
    }

    public Expression divide(Expression left, Expression right) {
        return divide(left, right, null);
    }

    public Expression divide(Expression left, Expression right, String name) {
        Expression result = graph.add(new Divide(left, right, name));
        left.addObserver(result);
        right.addObserver(result);
        return result;
    }

    public Expression group(Expression[] nodes) {
        return group(nodes, null);
    }

    public Expression group(Expression[] nodes, String name) {
        return graph.add(new Group(nodes, name));
    }

    public Expression matmul(Expression left, Expression right) {
        return matmul(left, right, false, false, null);
    }

    public Expression matmul(Expression left, Expression right, boolean transposeLeft, boolean transposeRight) {
        return matmul(left, right, transposeLeft, transposeRight, null);
    }

    public Expression matmul(Expression left, Expression right, boolean transposeLeft, boolean transposeRight, String name) {
        Expression result= graph.add(new MatMul(left, right, transposeLeft, transposeRight, name));
        left.addObserver(result);
        right.addObserver(result);
        return result;
    }

    public Expression multiAssign(Expression[] targets, Expression[] values) {
        return graph.add(new MultiAssign(targets, values, null));
    }

    public Expression multiply(Expression left, Expression right) {
        return multiply(left, right, null);
    }

    public Expression multiply(Expression left, Expression right, String name) {
        Expression result = graph.add(new Multiply(left, right, name));
        left.addObserver(result);
        right.addObserver(result);
        return result;
    }

    public Expression neg(Expression base) {
        return neg(base, null);
    }

    public Expression neg(Expression base, String name) {
        Expression result = graph.add(new Negate(base, name));
        base.addObserver(result);
        return result;
    }

    public Expression parameter(Tensor value) {
        return parameter(value, null);
    }

    public Expression parameter(Tensor value, String name) {
        return graph.add(new Parameter(value, name));
    }

    public Expression reduceSum(Expression base, int dimension) {
        return reduceSum(base, dimension, null);
    }

    public Expression reduceSum(Expression base, int dimension, String name) {
        if (dimension >= 0) {
            Expression result = graph.add(new ReduceSum(base, dimension, name));
            base.addObserver(result);
            return result;
        }
        return base;
    }

    public Expression sigmoid(Expression base) {
        return sigmoid(base, null);
    }

    public Expression sigmoid(Expression base, String name) {
        Expression result = graph.add(new Sigmoid(base, name));
        base.addObserver(result);
        return result;
    }

    public Expression sigmoidGrad(Expression base) {
        return sigmoidGrad(base, null);
    }

    public Expression sigmoidGrad(Expression base, String name) {
        Expression result = graph.add(new SigmoidGrad(base, name));
        base.addObserver(result);
        return result;
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

    public Expression softmaxGrad(Expression base, Expression grad) {
        return softmaxGrad(base, grad, null);
    }

    public Expression softmaxGrad(Expression base, Expression grad, String name) {
        return graph.add(new SoftmaxGrad(base, grad, name));
    }

    public Expression square(Expression base, String name) {
        Expression result = graph.add(new Square(base, name));
        base.addObserver(result);
        return result;
    }

    public Expression step(Expression base) {
        return step(base, null);
    }

    public Expression step(Expression base, String name) {
        return graph.add(new Step(base, name));
    }

    public Expression subtract(Expression left, Expression right) {
        return subtract(left, right, null);
    }

    public Expression subtract(Expression left, Expression right, String name) {
        Expression result = graph.add(new Subtract(left, right, name));
        left.addObserver(result);
        right.addObserver(result);
        return result;
    }

    public Expression tanGrad(Expression base) {
        return tanGrad(base, null);
    }

    public Expression tanGrad(Expression base, String name) {
        return graph.add(new TangentGrad(base, name));
    }

    public Expression tile(Expression base, int[] scale) {
        return tile(base, scale, null);
    }

    public Expression tile(Expression base, int[] scale, String name) {
        Expression result = graph.add(new Tile(base, scale, name));
        base.addObserver(result);
        return result;
    }
}
