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
import io.luan.learn4j.structure.impl.special.Fill;
import io.luan.learn4j.structure.impl.special.Group;
import io.luan.learn4j.structure.impl.transform.*;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.val;

import java.lang.reflect.Array;
import java.util.Arrays;

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
    private static Graph activeGraph = new GraphImpl("DEFAULT");

    public static Expression abs(Expression base) {
        return abs(base, null);
    }

    public static Expression abs(Expression base, String name) {
        return addToGraph(new Absolute(base, name));
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

    public static Expression constant(Tensor tensor) {
        return constant(tensor, null);
    }

    public static Expression constant(Tensor tensor, String name) {
        return addToGraph(new Constant(tensor, name));
    }

    public static Expression cos(Expression base) {
        return cos(base, null);
    }

    public static Expression cos(Expression base, String name) {
        return addToGraph(new Cosine(base, name));
    }

    public static Expression divide(Expression left, Expression right) {
        return divide(left, right, null);
    }

    public static Expression divide(Expression left, Expression right, String name) {
        return addToGraph(new Divide(left, right, name));
    }

    public static Expression exp(Expression base) {
        return exp(base, null);
    }

    public static Expression exp(Expression base, String name) {
        return addToGraph(new Exponential(base, name));
    }

    public static Expression fill(Number scalar, int[] shape) {
        return fill(scalar, shape, null);
    }

    public static Expression fill(Number scalar, int[] shape, String name) {
        return addToGraph(new Fill(scalar, shape, name));
    }

    public static GradientDescentOptimizer gradientDescentOptimizer(double learnRate) {
        val optimizer = new GradientDescentOptimizer(activeGraph, learnRate);
        return optimizer;
    }

    public static Expression gradients(Expression source, Expression node) {
        return gradients(source, node, null);
    }

    public static Expression gradients(Expression source, Expression node, Expression grad) {
        val visitor = new ReverseGradientVisitor(activeGraph);
        visitor.visit(source, grad);
        return source.getGradient(node);
    }

    public static Expression[] gradients(Expression source, Expression[] nodes) {
        return gradients(source, nodes, null);
    }

    public static Expression[] gradients(Expression source, Expression[] nodes, Expression grad) {
        val visitor = new ReverseGradientVisitor(activeGraph);
        visitor.visit(source, grad);

        Expression[] gradients = new Expression[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            gradients[i] = source.getGradient(nodes[i]);
//            if (this.interactive) {
//                grad.eval();
//            }
        }
        return gradients;
    }

    public static Expression group(Expression... expressions) {
        return group(expressions, null);
    }

    public static Expression group(Expression[] expressions, String name) {
        return addToGraph(new Group(expressions, name));
    }

    public static Expression log(Expression base) {
        return log(base, null);
    }

    public static Expression log(Expression base, String name) {
        return addToGraph(new Logarithm(base, name));
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

    public static Expression negate(Expression base) {
        return negate(base, null);
    }

    public static Expression negate(Expression base, String name) {
        return addToGraph(new Negate(base, name));
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

    public static Expression reciprocal(Expression base) {
        return reciprocal(base, null);
    }

    public static Expression reciprocal(Expression base, String name) {
        return addToGraph(new Reciprocal(base, name));
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

    public static Expression sign(Expression base) {
        return sign(base, null);
    }

    public static Expression sign(Expression base, String name) {
        return addToGraph(new Sign(base, name));
    }

    public static Expression sin(Expression base) {
        return sin(base, null);
    }

    public static Expression sin(Expression base, String name) {
        return addToGraph(new Sine(base, name));
    }

    public static Expression softmax(Expression base) {
        return softmax(base, null);
    }

    public static Expression softmax(Expression base, String name) {
        return addToGraph(new Softmax(base, name));
    }

    public static Expression sqrt(Expression base) {
        return sqrt(base, null);
    }

    public static Expression sqrt(Expression base, String name) {
        return addToGraph(new SquareRoot(base, name));
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

    public static Expression tan(Expression base) {
        return tan(base, null);
    }

    public static Expression tan(Expression base, String name) {
        return addToGraph(new Tangent(base, name));
    }

    public static Expression tanh(Expression base) {
        return tanh(base, null);
    }

    public static Expression tanh(Expression base, String name) {
        return addToGraph(new Tanh(base, name));
    }

    public static Expression variable(int[] shape) {
        return variable(shape, null);
    }

    public static Expression variable(int[] shape, String name) {
        return addToGraph(new Variable(shape, name));
    }

    private static Expression addToGraph(Expression exp) {
        return activeGraph != null ? activeGraph.add(exp) : exp;
    }

}
