package io.luan.learn4j.structure;

import lombok.Getter;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * A Tensor in Mathematics is similar to a multi-dimensional array.
 * <p>
 * The Tensor class in Learn4j is a wrapper class to the underlying data store
 * <p>
 * For now the data store is ND4J.INDArray, but may change later
 *
 * @author Guangmiao Luan
 * @since 24/09/2017.
 */
public class Tensor {

    /**
     * The underlying value of the tensor.
     * This MAY change to a different type
     */
    @Getter
    private INDArray value;

    private Tensor(INDArray value) {
        this.value = value;
    }

    public static Tensor create(int[] data) {
        return new Tensor(Nd4j.create(data));
    }

    public static Tensor create(double[] data) {
        return new Tensor(Nd4j.create(data));
    }

    public static Tensor create(float[] data) {
        return new Tensor(Nd4j.create(data));
    }

    public static Tensor create(INDArray array) {
        return new Tensor(array);
    }

    public static Tensor create(double[] data, int[] shape) {
        return new Tensor(Nd4j.create(data, shape));
    }

    public static Tensor create(float[] data, int[] shape) {
        return new Tensor(Nd4j.create(data, shape));
    }

    public static Tensor ones(int columns) {
        return new Tensor(Nd4j.ones(columns));
    }

    public static Tensor ones(int rows, int columns) {
        return new Tensor(Nd4j.ones(rows, columns));
    }

    public static Tensor rand(int columns) {
        return new Tensor(Nd4j.rand(new int[]{1, columns}));
    }

    public static Tensor rand(int rows, int columns) {
        return new Tensor(Nd4j.rand(rows, columns));
    }

    public static Tensor scalar(Number value) {
        return new Tensor(Nd4j.scalar(value));
    }

    public static Tensor scalars(Number value, int columns) {
        return new Tensor(Nd4j.zeros(columns).addi(value));
    }

    public static Tensor scalars(Number value, int rows, int columns) {
        return new Tensor(Nd4j.zeros(rows, columns).addi(value));
    }

    public static Tensor zeros(int columns) {
        return new Tensor(Nd4j.zeros(columns));
    }

    public static Tensor zeros(int rows, int columns) {
        return new Tensor(Nd4j.zeros(rows, columns));
    }

    @Override
    public String toString() {
        if (this.value.isScalar()) {
            return "" + this.value.getDouble(0);
        }
        return this.value.toString();
    }
}
