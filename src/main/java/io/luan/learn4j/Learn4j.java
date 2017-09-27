package io.luan.learn4j;

import io.luan.learn4j.expression.*;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
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
     */
    private static Graph activeGraph;

    private static List<Graph> graphList = new ArrayList<Graph>();

    public static Expression add(Expression left, Expression right) {
        return add("", left, right);
    }

    public static Expression add(String name, Expression left, Expression right) {
        return addToGraph(new Add(name, left, right));
    }

    public static Expression constant(String name, Tensor tensor) {
        return addToGraph(new Constant(name, tensor));
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

    public static Expression mul(Expression left, Expression right) {
        return mul("", left, right);
    }

    public static Expression mul(String name, Expression left, Expression right) {
        return addToGraph(new Multiply(name, left, right));
    }

    public static Expression parameter(String name, Tensor tensor) {
        return addToGraph(new Parameter(name, tensor.getValue()));
    }

    public static Expression reduceMean(Expression base) {
        return reduceMean("", base);
    }

    public static Expression reduceMean(String name, Expression base) {
        return addToGraph(new ReduceMean(name, base, 0));
    }

    public static Session session(String s) {
        Session sess = new Session(activeGraph);
        return sess;
    }

    public static Expression square(Expression base) {
        return square("", base);
    }

    public static Expression square(String name, Expression base) {
        return addToGraph(new Square(name, base));
    }

    public static Expression sub(Expression left, Expression right) {
        return sub("", left, right);
    }

    public static Expression sub(String name, Expression left, Expression right) {
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
