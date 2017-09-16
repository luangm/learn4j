package io.luan.learn4j;

import io.luan.learn4j.expression.*;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * This is a main factory class for creating nodes
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Learn4j {

    public static MatMul mmul(Expression left, Expression right) {
        return new MatMul(null, left, right);
    }

    public static MatMul mmul(String name, Expression left, Expression right) {
        return new MatMul(name, left, right);
    }

    public static Add add(String name, Expression left, Expression right) {
        return new Add(name, left, right);
    }

    public static Multiply mul(String name, Expression left, Expression right) {
        return new Multiply(name, left, right);
    }

    public static Add add(Expression left, Expression right) {
        return new Add(null, left, right);
    }

    public static Relu relu(Expression exp) {
        return new Relu();
    }

    public static Parameter parameter(String name, INDArray data) {
        return new Parameter(name, data);
    }

    public static Variable variable(String name, int[] shape) {
        return new Variable(name, shape);
    }

}
