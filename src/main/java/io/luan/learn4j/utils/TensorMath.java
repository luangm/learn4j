package io.luan.learn4j.utils;

import io.luan.learn4j.structure.Tensor;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

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

    public static Tensor matmul(Tensor left, Tensor right, boolean transposeLeft, boolean transposeRight) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        if (transposeLeft) {
            leftArray = leftArray.transpose();
        }

        if (transposeRight) {
            rightArray = rightArray.transpose();
        }

        INDArray prod = leftArray.mmul(rightArray);
        return Tensor.create(prod);
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

    public static Tensor negate(Tensor base) {
        INDArray array = base.getValue();
        INDArray neg = array.neg();
        return Tensor.create(neg);
    }

    public static Tensor reduceMean(Tensor base) {
        INDArray array = base.getValue();
        INDArray mean = Nd4j.mean(array);
        return Tensor.create(mean);
    }

    public static Tensor sigmoid(Tensor base) {
        INDArray array = base.getValue();
        INDArray sigmoid = Transforms.sigmoid(array, true);
        return Tensor.create(sigmoid);
    }

    public static Tensor sigmoidGrad(Tensor base) {
        INDArray array = base.getValue();
        INDArray sigGrad = Transforms.sigmoidDerivative(array, true);
        return Tensor.create(sigGrad);
    }

    public static Tensor square(Tensor base) {
        INDArray array = base.getValue();
        INDArray squared = array.mul(array);
        return Tensor.create(squared);
    }

    public static Tensor subtract(Tensor left, Tensor right) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        INDArray diff = leftArray.dup().subi(rightArray);
        return Tensor.create(diff);
    }
}
