package io.luan.learn4j.core.utils;

import io.luan.learn4j.core.Tensor;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.util.Arrays;

/**
 * Utility class for encapsulate all Tensor operations
 *
 * @author Guangmiao Luan
 * @since 27/09/2017.
 */
public class TensorMath {

    public static Tensor abs(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.abs(array, true);
        return Tensor.create(result);
    }

    public static Tensor add(Tensor left, Tensor right) {
        return add(left, right, null);
    }

    public static Tensor add(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        if (result != null && !Arrays.equals(result.getShape(), resultShape)) {
            throw new IllegalArgumentException("Result's shape is not compatible with inputs");
        }

        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        if (result != null) {
            INDArray resultArray = result.getArray();
            leftArray.addi(rightArray, resultArray);
            return result;
        }

        return Tensor.create(leftArray.add(rightArray));
    }

    public static Tensor divide(Tensor left, Tensor right) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        INDArray div = leftArray.div(rightArray);
        return Tensor.create(div);
    }

    public static Tensor divide(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();
        INDArray resultArray = result.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        leftArray.div(rightArray, resultArray);
        return result;
    }

    public static Tensor matmul(Tensor left, Tensor right, boolean transposeLeft, boolean transposeRight) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        if (transposeLeft) {
            leftArray = leftArray.transpose();
        }

        if (transposeRight) {
            rightArray = rightArray.transpose();
        }

        INDArray prod = leftArray.mmul(rightArray);
        return Tensor.create(prod);
    }

    public static Tensor matmul(Tensor left, Tensor right, boolean transposeLeft, boolean transposeRight, Tensor result) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        if (transposeLeft) {
            leftArray = leftArray.transpose();
        }

        if (transposeRight) {
            rightArray = rightArray.transpose();
        }

        leftArray.mmul(rightArray, result.getArray());
        return result;
    }

    /**
     * This is the Hadamard Product (element-wise product)
     */
    public static Tensor multiply(Tensor left, Tensor right) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        INDArray prod = leftArray.mul(rightArray);
        return Tensor.create(prod);
    }

    public static Tensor multiply(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();
        INDArray resultArray = result.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        leftArray.mul(rightArray, resultArray);
        return result;
    }

    public static Tensor negate(Tensor base) {
        INDArray array = base.getArray();
        INDArray neg = array.negi();
        return base;
//        return Tensor.create(neg);
    }

    public static Tensor reduceMean(Tensor base) {
        INDArray array = base.getArray();
        INDArray mean = Nd4j.mean(array);
        return Tensor.create(mean);
    }

    public static Tensor reduceSum(Tensor base, int dimension) {
        INDArray array = base.getArray();
        INDArray sum;
        if (dimension != -1) {
            sum = Nd4j.sum(array, dimension);
        } else {
            sum = Nd4j.sum(array);
        }
        return Tensor.create(sum);
    }

    public static Tensor relu(Tensor base) {
        INDArray array = base.getArray();
        INDArray relu = Transforms.relu(array, true);
        return Tensor.create(relu);
    }

    public static Tensor sigmoid(Tensor base) {
        INDArray array = base.getArray();
        INDArray sigmoid = Transforms.sigmoid(array, true);
        return Tensor.create(sigmoid);
    }

    public static Tensor sigmoidGrad(Tensor base) {
        INDArray array = base.getArray();
        INDArray sigGrad = Transforms.sigmoidDerivative(array, true);
        return Tensor.create(sigGrad);
    }

    public static Tensor sign(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.sign(array, true);
        return Tensor.create(result);
    }

    public static Tensor square(Tensor base) {
        INDArray array = base.getArray();
        INDArray squared = array.mul(array);
        return Tensor.create(squared);
    }

    public static Tensor step(Tensor base) {
        INDArray array = base.getArray();
        INDArray step = Transforms.step(array, true);
        return Tensor.create(step);
    }

    public static Tensor subtract(Tensor left, Tensor right) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        INDArray diff = leftArray.sub(rightArray);
        return Tensor.create(diff);
    }

    public static Tensor subtract(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getArray();
        INDArray rightArray = right.getArray();
        INDArray resultArray = result.getArray();

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());
        leftArray = leftArray.broadcast(resultShape);
        rightArray = rightArray.broadcast(resultShape);

        leftArray.sub(rightArray, resultArray);
        return result;
    }
}
