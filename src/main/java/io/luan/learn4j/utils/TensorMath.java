package io.luan.learn4j.utils;

import io.luan.learn4j.structure.Tensor;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.accum.Sum;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import static io.luan.learn4j.Learn4j.println;

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

//        println("Left: " + leftArray + leftArray.shapeInfoToString());
//        println("Right: " + rightArray + rightArray.shapeInfoToString());

//        println(ShapeUtils.printShape(leftArray.shape()));
//        println(ShapeUtils.printShape(rightArray.shape()));

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());

//        println(ShapeUtils.printShape(resultShape));

        if (!ShapeUtils.isSameShape(leftArray.shape(), resultShape)) {
            leftArray = leftArray.broadcast(resultShape);
        }

        if (!ShapeUtils.isSameShape(rightArray.shape(), resultShape)) {
            rightArray = rightArray.broadcast(resultShape);
        }

        INDArray sum = leftArray.add(rightArray);
        return Tensor.create(sum);
    }

    public static Tensor add(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();
        INDArray resultArray = result.getValue();

        // INPLACE add
        leftArray.add(rightArray, resultArray);
//        INDArray sum = leftArray.add(rightArray);
//        return Tensor.create(sum);

        return result;
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

    public static Tensor matmul(Tensor left, Tensor right, boolean transposeLeft, boolean transposeRight, Tensor result) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();

        if (transposeLeft) {
            leftArray = leftArray.transpose();
        }

        if (transposeRight) {
            rightArray = rightArray.transpose();
        }

        leftArray.mmul(rightArray, result.getValue());
        return result;
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

    public static Tensor multiply(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();
        INDArray resultArray = result.getValue();

        leftArray.mul(rightArray, resultArray);
        return result;
    }

    public static Tensor negate(Tensor base) {
        INDArray array = base.getValue();
        INDArray neg = array.negi();
        return base;
//        return Tensor.create(neg);
    }

    public static Tensor reduceMean(Tensor base) {
        INDArray array = base.getValue();
        INDArray mean = Nd4j.mean(array);
        return Tensor.create(mean);
    }

    public static Tensor reduceSum(Tensor base, int dimension) {
        INDArray array = base.getValue();
        INDArray sum;
        if (dimension != -1) {
            sum = Nd4j.sum(array, dimension);
        } else {
            sum = Nd4j.sum(array);
        }
        return Tensor.create(sum);
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

        int[] resultShape = ShapeUtils.broadcastShapes(leftArray.shape(), rightArray.shape());

//        println(ShapeUtils.printShape(resultShape));

        if (!ShapeUtils.isSameShape(leftArray.shape(), resultShape)) {
            leftArray = leftArray.broadcast(resultShape);
        }

        if (!ShapeUtils.isSameShape(rightArray.shape(), resultShape)) {
            rightArray = rightArray.broadcast(resultShape);
        }

        INDArray diff = leftArray.sub(rightArray);
        return Tensor.create(diff);
    }

    public static Tensor subtract(Tensor left, Tensor right, Tensor result) {
        INDArray leftArray = left.getValue();
        INDArray rightArray = right.getValue();
        INDArray resultArray = result.getValue();

        leftArray.sub(rightArray, resultArray);
        return result;
    }

    private static int[] mergeShapes(int[] shape1, int[] shape2) {

        int maxRank = Math.max(shape1.length, shape2.length);

        return null;
    }
}
