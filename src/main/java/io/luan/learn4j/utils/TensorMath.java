package io.luan.learn4j.utils;

import io.luan.learn4j.structure.Tensor;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Utility class for encapsulate all Tensor operations
 *
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public class TensorMath {

    public static Tensor add(Tensor left, Tensor right) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        INDArray sum = leftArray.add(rightArray);
        return Tensor.create(sum);
    }

    /**
     * This is the Hadamard Product (element-wise product)
     */
    public static Tensor multiply(Tensor left, Tensor right) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        INDArray prod = leftArray.mul(rightArray);
        return Tensor.create(prod);
    }

    public static Tensor subtract(Tensor left, Tensor right) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        INDArray diff = leftArray.dup().subi(rightArray);
        return Tensor.create(diff);
    }
}
