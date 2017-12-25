package io.luan.learn4j.test.basics;

import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.convolution.Convolution;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestNd4J {

    @Test
    public void testBroadcast() {
        INDArray array = Nd4j.linspace(1, 6, 6).reshape(2, 3);
        val result = Nd4j.sum(array);
        System.out.println(result);

//
//        System.out.println(array);
//        System.out.println(array.shapeInfoToString());
//
//        INDArray other = array.transpose();
//
//        System.out.println(other);
//        System.out.println(other.shapeInfoToString());
////        other.put(new int[]{0, 1, 2}, Nd4j.zeros(1, 1).addi(9999));
//
//        System.out.println(array);

//        INDArray one = Nd4j.linspace(1, 2, 2).broadcast(3, 2);
//        System.out.println(one);
//        System.out.println(one.shapeInfoToString());
//
//
//        INDArray sum = array.add(one);
//
//        System.out.println(sum);
    }

    @Test
    public void testConv2() {
        INDArray nd = Nd4j.ones(5,5);
        INDArray kernel = Nd4j.ones(3,3);
        INDArray conv = Nd4j.getConvolution().conv2d(nd, kernel, Convolution.Type.VALID);
        System.out.println(conv);
    }

    @Test
    public void testConv() {
        int nEx = 1;
        int depth = 1;
        int width = 3;
        int height = 3;
        int prod = nEx * depth * width * height;
        INDArray in = Nd4j.linspace(1, prod, prod).reshape(nEx, depth, width, height);
        System.out.println(in);
//        int[] kernel = new int[]{2, 2};
//        int[] stride = new int[]{1, 1};
//        int[] padding = new int[]{0, 0};
//        INDArray out = Convolution.im2col(in, kernel, stride, padding);
//        System.out.println(out);
//        System.out.println(out.shapeInfoToString());

        val myKernel = Nd4j.ones(1, 1, 1, 1);
        INDArray out2 = Convolution.conv2d(in, myKernel, Convolution.Type.SAME);
        System.out.println(out2);
        System.out.println(out2.shapeInfoToString());
    }

    @Test
    public void testIm2Col() {
        int nEx = 1;
        int depth = 1;
        int width = 5;
        int height = 5;
        int prod = nEx * depth * width * height;
        INDArray in = Nd4j.linspace(1, prod, prod).reshape(nEx, depth, width, height);
        System.out.println(in);
        int[] kernel = new int[]{2, 2};
        int[] stride = new int[]{1, 1};
        int[] padding = new int[]{0, 0};
        INDArray out = Convolution.im2col(in, kernel, stride, padding);
        System.out.println(out);
        System.out.println(out.shapeInfoToString());

    }

    @Test
    public void testMatmul() {
        INDArray a = Nd4j.linspace(1, 10, 6).reshape(2, 3);
        INDArray b = Nd4j.linspace(1, 3, 3).reshape(3, 1);
        INDArray c = a.mmul(b);


        System.out.println(c);
        System.out.println(c.shapeInfoToString());

        System.out.println(c.shape());
    }

    @Test
    public void testSigmoid() {
        INDArray array = Nd4j.linspace(-2, 2, 6).reshape(3, 2);
        System.out.println(array);

        INDArray sig2 = Transforms.sigmoid(array, true);
        System.out.println(sig2);

        INDArray sigDev = Transforms.sigmoidDerivative(array, true);
        System.out.println(sigDev);
    }

    @Test
    public void testTranspose() {
        INDArray testConst = Nd4j.linspace(1, 10, 6);
        INDArray test2 = testConst.reshape(3, 2);

        System.out.println(testConst);
        System.out.println(testConst.shapeInfoToString());
        System.out.println("-----");

        System.out.println(test2);
        System.out.println(test2.shapeInfoToString());
        System.out.println("-----");


        INDArray array = Nd4j.ones(4, 4);
        System.out.println(array);

        INDArray transposed = array.transpose();
        System.out.println(transposed);

        array.putScalar(1, 2, 5.0);
        System.out.println(array);

        INDArray sum = array.sum(0);
        System.out.println(sum);
        System.out.println(sum.shapeInfoToString());
        System.out.println(sum.rank());


        System.out.println("----------");


        INDArray nd2 = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, new int[]{2, 6});
        System.out.println(nd2);


        INDArray nd34 = nd2.reshape(3, 4);
        System.out.println(nd34);


        nd2.putScalar(1, 2, 999);

        System.out.println(nd34);


        System.out.println("----------");


        INDArray ddd3 = Nd4j.create(new float[]{-1, 2, -3, 4, -5, 6}, new int[]{2, 3});
        System.out.println(ddd3);


        INDArray abs = Transforms.abs(ddd3);
        System.out.println(abs);

        System.out.println(ddd3);


    }
}
