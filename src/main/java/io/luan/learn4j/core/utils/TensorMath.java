package io.luan.learn4j.core.utils;

import io.luan.learn4j.core.Tensor;
import lombok.val;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.Tan;
import org.nd4j.linalg.api.ops.impl.transforms.TanDerivative;
import org.nd4j.linalg.convolution.Convolution;
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

    public static Tensor conv2d(Tensor image, Tensor kernel, int[] stride, int[] padding) {
        INDArray imageArray = image.getArray();
        if (imageArray.rank() != 4) {
            throw new IllegalArgumentException("Image Rank must be 4");
        }

        INDArray kernelArray = kernel.getArray();
        if (kernelArray.rank() != 4) {
            throw new IllegalArgumentException("Kernel Rank must be 4");
        }

        int numImages = imageArray.shape()[0];
        int channels = imageArray.shape()[1];
        int height = imageArray.shape()[2];
        int width = imageArray.shape()[3];

        int numKernels = kernelArray.shape()[0];
        int kernelChannels = kernelArray.shape()[1];
        int kernelHeight = kernelArray.shape()[2];
        int kernelWidth = kernelArray.shape()[3];

        int outHeight = ConvUtils.outSize(height, kernelHeight, stride[0], padding[0]);
        int outWidth = ConvUtils.outSize(width, kernelWidth, stride[1], padding[1]);

        INDArray xCol = im2col(image, new int[]{kernelHeight, kernelWidth}, stride, padding).getArray();
        INDArray kCol = kernelArray.reshape(numKernels, kernelChannels * kernelWidth * kernelHeight);
        INDArray result = kCol.mmul(xCol);
        INDArray reshaped = result.reshape(numKernels, numImages, outHeight, outWidth);
        INDArray transposed = reshaped.permute(1, 0, 2, 3);

        return Tensor.create(transposed);
    }

    public static Tensor cos(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.cos(array, true);
        return Tensor.create(result);
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

    public static Tensor exp(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.exp(array, true);
        return Tensor.create(result);
    }

    public static Tensor conv2dImageGrad(Tensor image, Tensor kernel, Tensor grad) {
        int numKernels = kernel.getShape()[0];
        INDArray gradArray = grad.getArray();
        INDArray gradReshape = gradArray.reshape(numKernels, gradArray.length() / numKernels);

        INDArray kernelArray = kernel.getArray();
        INDArray kReshape = kernelArray.reshape(numKernels, kernelArray.length() / numKernels);

        INDArray col = matmul(kReshape, gradReshape, true, false);

        return null;
//        return col2im(col, );
//        return TensorUtils.col2im(col, image, kernel).reshape(image.shape);
    }

    // TODO: Finish
    public static Tensor col2im(Tensor col, int[] kernel, int[] stride, int[] padding) {
        INDArray colArray = col.getArray();

        // The original col
        // Nd4j expects col2im's col be rank 6: N, C, KH, KW, OH, OW


        // output of col2im is N, C, H, W
        return null;
    }

    public static Tensor im2col(Tensor image, int[] kernel, int[] stride, int[] padding) {
        INDArray imageArray = image.getArray();
        if (imageArray.rank() != 4) {
            throw new IllegalArgumentException("Image Rank must be 4");
        }
        int numImages = imageArray.shape()[0];
        int channels = imageArray.shape()[1];
        int height = imageArray.shape()[2];
        int width = imageArray.shape()[3];

        int outHeight = ConvUtils.outSize(height, kernel[0], stride[0], padding[0]);
        int outWidth = ConvUtils.outSize(width, kernel[1], stride[1], padding[1]);

        int resultHeight = channels * kernel[0] * kernel[1];
        int resultWidth = numImages * outWidth * outHeight;

        // N, C, KH, KW, OH, OW
        INDArray im2colArray = Convolution.im2col(imageArray, kernel, stride, padding);
        im2colArray = im2colArray.permute(1, 2, 3, 0, 4, 5);
        im2colArray = im2colArray.reshape(resultHeight, resultWidth);
        return Tensor.create(im2colArray);
    }

    public static Tensor log(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.log(array, true);
        return Tensor.create(result);
    }

    private static INDArray matmul(INDArray left, INDArray right, boolean transposeLeft, boolean transposeRight) {
        if (transposeLeft) {
            left = left.transpose();
        }

        if (transposeRight) {
            right = right.transpose();
        }

        return left.mmul(right);
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
        INDArray result = array.neg();
        return Tensor.create(result);
    }

    public static Tensor reciprocal(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = array.rdiv(1);
        return Tensor.create(result);
    }

    public static Tensor reduceMax(Tensor base, int dimension) {
        INDArray array = base.getArray();
        INDArray max = dimension != -1 ? Nd4j.max(array, dimension) : Nd4j.max(array);
        return Tensor.create(max);
    }

    public static Tensor reduceMean(Tensor base, int dimension) {
        INDArray array = base.getArray();
        INDArray mean = dimension != -1 ? Nd4j.mean(array, dimension) : Nd4j.mean(array);
        return Tensor.create(mean);
    }

    public static Tensor reduceMin(Tensor base, int dimension) {
        INDArray array = base.getArray();
        INDArray min = dimension != -1 ? Nd4j.min(array, dimension) : Nd4j.min(array);
        return Tensor.create(min);
    }

    public static Tensor reduceSum(Tensor base, int dimension) {
        INDArray array = base.getArray();
        INDArray sum = dimension != -1 ? Nd4j.sum(array, dimension) : Nd4j.sum(array);
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

    public static Tensor sin(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.sin(array, true);
        return Tensor.create(result);
    }

    public static Tensor softmax(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.softmax(array, true);
        return Tensor.create(result);
    }

    public static Tensor softmaxGrad(Tensor base, Tensor grad) {
        INDArray array = base.getArray();
        INDArray gradArray = grad.getArray();
        INDArray softmax = Transforms.softmax(array, true);
        INDArray mul = softmax.muli(gradArray);
        INDArray sum = mul.sum(1);
        INDArray subtract = subtract(gradArray, sum);
        INDArray result = subtract.mul(softmax);
        return Tensor.create(result);
    }

    public static Tensor sqrt(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.sqrt(array, true);
        return Tensor.create(result);
    }

    public static Tensor square(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = array.mul(array);
        return Tensor.create(result);
    }

    public static Tensor step(Tensor base) {
        INDArray array = base.getArray();
        INDArray step = Transforms.step(array, true);
        return Tensor.create(step);
    }

    public static Tensor subtract(Tensor left, Tensor right) {
        INDArray result = subtract(left.getArray(), right.getArray());
        return Tensor.create(result);
    }

    public static Tensor subtract(Tensor left, Tensor right, Tensor result) {
        subtract(left.getArray(), right.getArray(), result.getArray());
        return result;
    }

    public static Tensor tan(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Nd4j.getExecutioner().execAndReturn(new Tan(array, array.dup()));
        return Tensor.create(result);
    }

    public static Tensor tanGrad(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Nd4j.getExecutioner().execAndReturn(new TanDerivative(array, array.dup()));
        return Tensor.create(result);
    }

    public static Tensor tanh(Tensor base) {
        INDArray array = base.getArray();
        INDArray result = Transforms.tanh(array, true);
        return Tensor.create(result);
    }

    public static Tensor tile(Tensor base, int[] repeats) {
        INDArray array = base.getArray();
        INDArray result = Nd4j.tile(array, repeats);
        return Tensor.create(result);
    }

    public static Tensor softmaxCrossEntropyWithLogits(Tensor labels, Tensor logits, int dim) {
        if (dim < 0) {
            dim += logits.getRank();
        }
        val logSumExp = TensorMath.logSumExp(logits, -1);
        val sub = TensorMath.subtract(logits, logSumExp);
        val mul = TensorMath.multiply(labels, sub);
        val sum = TensorMath.reduceSum(mul, dim);
        return TensorMath.negate(sum);
    }

    public static Tensor logSumExp(Tensor base, int dim) {
        if (dim < 0) {
            dim += base.getRank();
        }
        val max = TensorMath.reduceMax(base, dim);
        val subtract = TensorMath.subtract(base, max);
        val exp = TensorMath.exp(subtract);
        val sum = TensorMath.reduceSum(exp, dim);
        val log = TensorMath.log(sum);
        return TensorMath.add(log, max);
    }


    private static INDArray subtract(INDArray left, INDArray right) {
        int[] resultShape = ShapeUtils.broadcastShapes(left.shape(), right.shape());
        left = left.broadcast(resultShape);
        right = right.broadcast(resultShape);
        return left.sub(right);
    }

    private static void subtract(INDArray left, INDArray right, INDArray result) {
        int[] resultShape = ShapeUtils.broadcastShapes(left.shape(), right.shape());
        left = left.broadcast(resultShape);
        right = right.broadcast(resultShape);
        left.sub(right, result);
    }
}
