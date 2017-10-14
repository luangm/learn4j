package io.luan.learn4j.structure;

import io.luan.learn4j.structure.impl.TensorImpl;
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
public interface Tensor {

    static Tensor create(int[] data) {
        return new TensorImpl(Nd4j.create(data));
    }

    static Tensor create(double[] data) {
        return new TensorImpl(Nd4j.create(data));
    }

    static Tensor create(float[] data) {
        return new TensorImpl(Nd4j.create(data));
    }

    static Tensor create(INDArray array) {
        return new TensorImpl(array);
    }

    static Tensor create(double[] data, int[] shape) {
        return new TensorImpl(Nd4j.create(data, shape));
    }

    static Tensor create(float[] data, int[] shape) {
        return new TensorImpl(Nd4j.create(data, shape));
    }

    static Tensor fill(Number scalar, int[] shape) {
        return new TensorImpl(Nd4j.zeros(shape).addi(scalar));
    }

    static Tensor ones(int columns) {
        return new TensorImpl(Nd4j.ones(columns));
    }

    static Tensor ones(int rows, int columns) {
        return new TensorImpl(Nd4j.ones(rows, columns));
    }

    static Tensor rand(int columns) {
        return new TensorImpl(Nd4j.rand(new int[]{1, columns}));
    }

    static Tensor rand(int rows, int columns) {
        return new TensorImpl(Nd4j.rand(rows, columns));
    }

    static Tensor scalar(Number value) {
        return new TensorImpl(Nd4j.scalar(value));
    }

    static Tensor scalars(Number value, int columns) {
        return new TensorImpl(Nd4j.zeros(columns).addi(value));
    }

    static Tensor scalars(Number value, int rows, int columns) {
        return new TensorImpl(Nd4j.zeros(rows, columns).addi(value));
    }

    static Tensor zeros(int columns) {
        return new TensorImpl(Nd4j.zeros(columns));
    }

    static Tensor zeros(int rows, int columns) {
        return new TensorImpl(Nd4j.zeros(rows, columns));
    }

    int getRank();

    int[] getShape();

    /**
     * The underlying value of the tensor.
     * This MAY change to a different type
     */
    INDArray getValue();
}
