package io.luan.learn4j;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.session.impl.SessionImpl;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.impl.GraphImpl;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.core.Variable;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.transform.*;
import lombok.val;

/**
 * This is a main factory class for creating nodes
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Learn4j {

    /**
     * Stores the current active graph.
     * All subsequent calls are based on this graph
     * This should be ThreadLocal
     */
    private static Graph activeGraph = new GraphImpl();

    public static Expression abs(Expression base) {
        return abs(base, null);
    }

    public static Expression abs(Expression base, String name) {
        return addToGraph(new Abs(base, name));
    }

    public static Expression add(Expression left, Expression right) {
        return add(left, right, null);
    }

    public static Expression add(Expression left, Expression right, String name) {
        return addToGraph(new Add(left, right, name));
    }

    public static Expression assign(Expression target, Expression newValue) {
        return null;
    }

    public static Expression constant(double[] data, int[] shape) {
        return constant(data, shape, null);
    }

    public static Expression constant(double[] data, int[] shape, String name) {
        return constant(Tensor.create(data, shape), name);
    }

    public static Expression constant(Tensor tensor, String name) {
        return addToGraph(new Constant(tensor, name));
    }

    public static Expression divide(Expression left, Expression right) {
        return divide(left, right, null);
    }

    public static Expression divide(Expression left, Expression right, String name) {
        return addToGraph(new Divide(left, right, name));
    }

    public static GradientDescentOptimizer gradientDescentOptimizer(double learnRate) {
        val optimizer = new GradientDescentOptimizer(activeGraph, learnRate);
        return optimizer;
    }

    public static Expression matmul(Expression left, Expression right) {
        return matmul(left, right, null);
    }

    public static Expression matmul(Expression left, Expression right, String name) {
        return matmul(left, right, false, false, name);
    }

    public static Expression matmul(Expression left, Expression right, boolean transposeLeft, boolean transposeRight, String name) {
        return addToGraph(new MatMul(left, right, transposeLeft, transposeRight, name));
    }

    public static Expression multiply(Expression left, Expression right) {
        return multiply(left, right, null);
    }

    public static Expression multiply(Expression left, Expression right, String name) {
        return addToGraph(new Multiply(left, right, name));
    }

    public static Expression parameter(Tensor tensor) {
        return parameter(tensor, null);
    }

    public static Expression parameter(Tensor tensor, String name) {
        return addToGraph(new Parameter(tensor, name));
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static Expression reduceMean(Expression base) {
        return reduceMean(base, null);
    }

    public static Expression reduceMean(Expression base, String name) {
        return reduceMean(base, -1, name);
    }

    public static Expression reduceMean(Expression base, int dimension) {
        return reduceMean(base, dimension, null);
    }

    public static Expression reduceMean(Expression base, int dimension, String name) {
        return addToGraph(new ReduceMean(base, dimension, name));
    }

    public static Expression reduceSum(Expression base) {
        return reduceSum(base, null);
    }

    public static Expression reduceSum(Expression base, String name) {
        return reduceSum(base, -1, name);
    }

    public static Expression reduceSum(Expression base, int dimension) {
        return reduceSum(base, dimension, null);
    }

    public static Expression reduceSum(Expression base, int dimension, String name) {
        return addToGraph(new ReduceSum(base, dimension, name));
    }

    public static Expression relu(Expression base) {
        return relu(base, null);
    }

    public static Expression relu(Expression base, String name) {
        return addToGraph(new Relu(base, name));
    }

    public static Session session(String s) {
        Session sess = new SessionImpl(activeGraph);
        return sess;
    }

    public static Expression sigmoid(Expression base) {
        return sigmoid(base, null);
    }

    public static Expression sigmoid(Expression base, String name) {
        return addToGraph(new Sigmoid(base, name));
    }

    public static Expression square(Expression base) {
        return square(base, null);
    }

    public static Expression square(Expression base, String name) {
        return addToGraph(new Square(base, name));
    }

    public static Expression step(Expression base) {
        return step(base, null);
    }

    public static Expression step(Expression base, String name) {
        return addToGraph(new Step(base, name));
    }

    public static Expression subtract(Expression left, Expression right) {
        return subtract(left, right, null);
    }

    public static Expression subtract(Expression left, Expression right, String name) {
        return addToGraph(new Subtract(left, right, name));
    }

    public static Expression variable(int[] shape) {
        return variable(shape, null);
    }

    public static Expression variable(int[] shape, String name) {
        return addToGraph(new Variable(shape, name));
    }

    private static Expression addToGraph(Expression exp) {
        if (activeGraph != null) {
            activeGraph.add(exp);
        }
        return exp;
    }

}
