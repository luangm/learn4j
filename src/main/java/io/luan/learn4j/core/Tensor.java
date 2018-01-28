package io.luan.learn4j.core;

import io.luan.learn4j.core.impl.TensorImpl;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * A Tensor in Mathematics is similar to a multi-dimensional array.
 * The Tensor interface defines some global creator
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

    static Tensor fill(Number scalar, int... shape) {
        return new TensorImpl(Nd4j.zeros(shape).addi(scalar));
    }

    static Tensor ones(int... shape) {
        return create(Nd4j.ones(shape));
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

    static Tensor zeros(int... shape) {
        return create(Nd4j.zeros(shape));
    }

    /**
     * The underlying value of the tensor.
     * This MAY change to a different type
     */
    INDArray getArray();

    int getRank();

    int[] getShape();
}
