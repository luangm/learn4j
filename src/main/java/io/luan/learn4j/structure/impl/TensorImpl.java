package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Tensor;
import lombok.Getter;
import org.nd4j.linalg.api.ndarray.INDArray;

import static io.luan.learn4j.utils.ShapeUtils.shapeToString;

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
public class TensorImpl implements Tensor {

    /**
     * The underlying value of the tensor.
     * This MAY change to a different type
     */
    @Getter
    private INDArray value;

    public TensorImpl(INDArray value) {
        this.value = value;
    }

    public int getRank() {
        return value.rank();
    }

    public int[] getShape() {
        return value.shape();
    }

    @Override
    public String toString() {
        if (this.value.isScalar()) {
            return "" + this.value.getDouble(0);
        }
        return this.value.toString() + " @ " + shapeToString(this.value.shape());
    }
}
