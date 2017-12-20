package io.luan.learn4j;

import io.luan.learn4j.optimizer.GradientDescentOptimizer;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.session.impl.SessionImpl;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.impl.*;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

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
     * <p>
     * This should be ThreadLocal
     */
    private static Graph activeGraph;

    /**
     * Stores all the graphs.
     * This should be shared among Threads
     */
    private static List<Graph> graphList = new ArrayList<Graph>();

    static {
        // Creates a default graph
        graph("DEFAULT");
    }

    public static Expression add(Expression left, Expression right) {
        return add("", left, right);
    }

    public static Expression add(String name, Expression left, Expression right) {
        return addToGraph(new Add(name, left, right));
    }

    public static Expression assign(Expression target, Expression newValue) {
        return null;
    }

    public static Expression constant(String name, Tensor tensor) {
        return addToGraph(new Constant(name, tensor));
    }

    public static Expression divide(Expression left, Expression right) {
        return divide("", left, right);
    }

    public static Expression divide(String name, Expression left, Expression right) {
        return addToGraph(new Divide(name, left, right));
    }

    public static GradientDescentOptimizer gradientDescentOptimizer(double learnRate) {
        val optimizer = new GradientDescentOptimizer(activeGraph, learnRate);
        return optimizer;
    }

    public static Graph graph(String name) {
        Graph graph = new GraphImpl();
        graphList.add(graph);
        activeGraph = graph;
        return graph;
    }

    public static Expression matmul(Expression left, Expression right) {
        return matmul("", left, right);
    }

    public static Expression matmul(String name, Expression left, Expression right) {
        return addToGraph(new MatMul(name, left, right));
    }

    public static Expression multiply(Expression left, Expression right) {
        return multiply("", left, right);
    }

    public static Expression multiply(String name, Expression left, Expression right) {
        return addToGraph(new Multiply(name, left, right));
    }

    public static Expression parameter(String name, Tensor tensor) {
        return addToGraph(new Parameter(name, tensor));
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static Expression reduceMean(Expression base) {
        return reduceMean("", base);
    }

    public static Expression reduceMean(String name, Expression base) {
        return addToGraph(new ReduceMean(name, base, 0));
    }

    public static Expression reduceSum(Expression base) {
        return reduceSum("", base);
    }

    public static Expression reduceSum(String name, Expression base) {
        return addToGraph(new ReduceSum(name, base, -1));
    }

    public static Session session(String s) {
        Session sess = new SessionImpl(activeGraph);
        return sess;
    }

    public static Expression sigmoid(Expression base) {
        return sigmoid("", base);
    }

    public static Expression sigmoid(String name, Expression base) {
        return addToGraph(new Sigmoid(name, base));
    }

    public static Expression relu(Expression base) {
        return relu("", base);
    }

    public static Expression relu(String name, Expression base) {
        return addToGraph(new Relu(name, base));
    }

    public static Expression step(Expression base) {
        return step("", base);
    }

    public static Expression step(String name, Expression base) {
        return addToGraph(new Step(name, base));
    }


    public static Expression square(Expression base) {
        return square("", base);
    }

    public static Expression square(String name, Expression base) {
        return addToGraph(new Square(name, base));
    }

    public static Expression subtract(Expression left, Expression right) {
        return subtract("", left, right);
    }

    public static Expression subtract(String name, Expression left, Expression right) {
        return addToGraph(new Subtract(name, left, right));
    }

    public static Expression variable(String name, int[] shape) {
        return addToGraph(new Variable(name, shape));
    }

    private static Expression addToGraph(Expression exp) {
        if (activeGraph != null) {
            activeGraph.add(exp);
        }
        return exp;
    }

}
