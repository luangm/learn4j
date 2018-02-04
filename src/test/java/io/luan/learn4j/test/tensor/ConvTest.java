package io.luan.learn4j.test.tensor;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.convolution.Convolution;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ConvTest {

    @Test
    public void testIm2Col() {
        val array = Nd4j.linspace(1, 18, 18).reshape(1, 2, 3, 3);
        int[] kernel = {2, 2};
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        INDArray result = Convolution.im2col(array, kernel, stride, padding);
        println(result);
        println(Arrays.toString(result.shape()));
        int outHeight = 8;
        int outWidth = 4;
        INDArray reshaped = result.reshape(outHeight, outWidth);
        println(reshaped);
    }

    @Test
    public void testIm2Col2() {
        val array = Tensor.create(Nd4j.linspace(1, 9, 9).reshape(1, 1, 3, 3));
        int[] kernel = {2, 2};
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        Tensor result = TensorMath.im2col(array, kernel, stride, padding);
        println(result);
    }

    @Test
    public void testIm2Col_nd4j() {
        val array = Tensor.create(Nd4j.linspace(1, 16, 16).reshape(1, 1, 4, 4));
        println(array);
        int[] kernel = {2, 2};
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        val col = Convolution.im2col(array.getArray(), kernel, stride, padding);
        println(col);

        val im = Convolution.col2im(col, stride, padding, 4, 4);
        println(im);
        println(im.shapeInfoToString());
    }

    @Test
    public void testConv2d() {
        val array = Tensor.create(Nd4j.linspace(1, 9, 9).reshape(1, 1, 3, 3));
        val kernel = Tensor.create(Nd4j.linspace(1, 4, 4).reshape(1, 1, 2, 2));
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        Tensor result = TensorMath.conv2d(array, kernel, stride, padding);
        println(result);
    }


    @Test
    public void testConv2d2() {
        val array = Tensor.create(Nd4j.linspace(1, 27, 27).reshape(3, 1, 3, 3));
        val kernel = Tensor.create(Nd4j.linspace(1, 8, 8).reshape(2, 1, 2, 2));
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        Tensor result = TensorMath.conv2d(array, kernel, stride, padding);
        println(result);
    }

    @Test
    public void testConv2d3() {
        val array = Tensor.create(Nd4j.linspace(1, 54, 54).reshape(2, 3, 3, 3));
        val kernel = Tensor.create(Nd4j.linspace(1, 24, 24).reshape(2, 3, 2, 2));
        int[] stride = {1, 1};
        int[] padding = {0, 0};
        Tensor result = TensorMath.conv2d(array, kernel, stride, padding);
        println(result);
    }
}

