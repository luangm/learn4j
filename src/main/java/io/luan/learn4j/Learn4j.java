package io.luan.learn4j;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.session.impl.SessionImpl;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.structure.impl.GraphImpl;
import io.luan.learn4j.structure.impl.reduction.ReduceMax;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceMin;
import io.luan.learn4j.structure.impl.special.AddN;
import io.luan.learn4j.structure.impl.special.Group;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
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
    private static Graph activeGraph;
    private static ExpressionFactory factory;

    static {
        activeGraph = new GraphImpl("DEFAULT");
        factory = new ExpressionFactory(activeGraph);
    }

    public static Expression abs(Expression base) {
        return factory.abs(base);
    }

    public static Expression abs(Expression base, String name) {
        return factory.abs(base, name);
    }

    public static Expression add(Expression left, Expression right) {
        return factory.add(left, right);
    }

    public static Expression add(Expression left, Expression right, String name) {
        return factory.add(left, right, name);
    }

    public static Expression addN(Expression... nodes) {
        return addN(nodes, null);
    }

    public static Expression addN(Expression[] nodes, String name) {
        return addToGraph(new AddN(nodes, name));
    }

    public static Expression assign(Expression target, Expression value) {
        return factory.assign(target, value);
    }

    public static Expression assign(Expression target, Expression value, String name) {
        return factory.assign(target, value, name);
    }

    public static Expression constant(Number scalar) {
        return factory.constant(scalar);
    }

    public static Expression constant(double[] data, int[] shape) {
        return constant(data, shape, null);
    }

    public static Expression constant(double[] data, int[] shape, String name) {
        return factory.constant(Tensor.create(data, shape), name);
    }

    public static Expression constant(Tensor tensor) {
        return factory.constant(tensor);
    }

    public static Expression constant(Tensor tensor, String name) {
        return factory.constant(tensor, name);
    }

    public static Expression cos(Expression base) {
        return factory.cos(base);
    }

    public static Expression cos(Expression base, String name) {
        return factory.cos(base, name);
    }

    public static Expression divide(Expression left, Expression right) {
        return factory.divide(left, right);
    }

    public static Expression divide(Expression left, Expression right, String name) {
        return factory.divide(left, right, name);
    }

    public static Expression exp(Expression base) {
        return factory.exp(base);
    }

    public static Expression exp(Expression base, String name) {
        return factory.exp(base, name);
    }

    public static Expression fill(Number scalar, int[] shape) {
        return factory.fill(scalar, shape);
    }

    public static Expression fill(Number scalar, int[] shape, String name) {
        return factory.fill(scalar, shape, name);
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
        return factory.log(base);
    }

    public static Expression log(Expression base, String name) {
        return factory.log(base, name);
    }

    public static Expression matmul(Expression left, Expression right) {
        return factory.matmul(left, right);
    }

    public static Expression matmul(Expression left, Expression right, String name) {
        return factory.matmul(left, right, false, false, name);
    }

    public static Expression matmul(Expression left, Expression right, boolean transposeLeft, boolean transposeRight, String name) {
        return factory.matmul(left, right, transposeLeft, transposeRight, name);
    }

    public static Expression multiply(Expression left, Expression right) {
        return factory.multiply(left, right);
    }

    public static Expression multiply(Expression left, Expression right, String name) {
        return factory.multiply(left, right, name);
    }

    public static Expression negate(Expression base) {
        return factory.neg(base);
    }

    public static Expression negate(Expression base, String name) {
        return factory.neg(base, name);
    }

    public static Expression parameter(Tensor tensor) {
        return factory.parameter(tensor);
    }

    public static Expression parameter(Tensor tensor, String name) {
        return factory.parameter(tensor, name);
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static Expression reciprocal(Expression base) {
        return factory.reciprocal(base);
    }

    public static Expression reciprocal(Expression base, String name) {
        return factory.reciprocal(base, name);
    }

    public static Expression reduceMax(Expression base) {
        return reduceMax(base, null);
    }

    public static Expression reduceMax(Expression base, String name) {
        return reduceMax(base, -1, name);
    }

    public static Expression reduceMax(Expression base, int dimension) {
        return reduceMax(base, dimension, null);
    }

    public static Expression reduceMax(Expression base, int dimension, String name) {
        return addToGraph(new ReduceMax(base, dimension, name));
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

    public static Expression reduceMin(Expression base) {
        return reduceMin(base, null);
    }

    public static Expression reduceMin(Expression base, String name) {
        return reduceMin(base, -1, name);
    }

    public static Expression reduceMin(Expression base, int dimension) {
        return reduceMin(base, dimension, null);
    }

    public static Expression reduceMin(Expression base, int dimension, String name) {
        return addToGraph(new ReduceMin(base, dimension, name));
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
        return factory.reduceSum(base, dimension, name);
    }

    public static Expression relu(Expression base) {
        return factory.relu(base);
    }

    public static Expression relu(Expression base, String name) {
        return factory.relu(base, name);
    }

    public static Session session(String s) {
        Session sess = new SessionImpl(activeGraph);
        return sess;
    }

    public static Expression sigmoid(Expression base) {
        return factory.sigmoid(base);
    }

    public static Expression sigmoid(Expression base, String name) {
        return factory.sigmoid(base, name);
    }

    public static Expression sign(Expression base) {
        return factory.sign(base);
    }

    public static Expression sign(Expression base, String name) {
        return factory.sign(base, name);
    }

    public static Expression sin(Expression base) {
        return factory.sin(base);
    }

    public static Expression sin(Expression base, String name) {
        return factory.sin(base, name);
    }

    public static Expression softmax(Expression base) {
        return factory.softmax(base);
    }

    public static Expression softmax(Expression base, String name) {
        return factory.softmax(base, name);
    }

    public static Expression softmaxCrossEntropy(Expression logits, Expression labels) {
        return factory.softmaxCrossEntropy(logits, labels);
    }

    public static Expression softmaxCrossEntropy(Expression logits, Expression labels, String name) {
        return factory.softmaxCrossEntropy(logits, labels, name);
    }

    public static Expression sqrt(Expression base) {
        return factory.sqrt(base);
    }

    public static Expression sqrt(Expression base, String name) {
        return factory.sqrt(base, name);
    }

    public static Expression square(Expression base) {
        return factory.square(base);
    }

    public static Expression square(Expression base, String name) {
        return factory.square(base, name);
    }

    public static Expression step(Expression base) {
        return factory.step(base);
    }

    public static Expression step(Expression base, String name) {
        return factory.step(base, name);
    }

    public static Expression subtract(Expression left, Expression right) {
        return factory.subtract(left, right);
    }

    public static Expression subtract(Expression left, Expression right, String name) {
        return factory.subtract(left, right, name);
    }

    public static Expression tan(Expression base) {
        return factory.tan(base);
    }

    public static Expression tan(Expression base, String name) {
        return factory.tan(base, name);
    }

    public static Expression tanh(Expression base) {
        return factory.tanh(base);
    }

    public static Expression tanh(Expression base, String name) {
        return factory.tanh(base, name);
    }

    public static Expression variable(int[] shape) {
        return factory.variable(shape);
    }

    public static Expression variable(int[] shape, String name) {
        return factory.variable(shape, name);
    }

    private static Expression addToGraph(Expression exp) {
        return activeGraph != null ? activeGraph.add(exp) : exp;
    }

}
