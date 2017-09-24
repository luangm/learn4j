package io.luan.learn4j.test.basics;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestNd4J {

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


        INDArray ddd3 = Nd4j.create(new float[]{-1, 2, -3 ,4,-5,6}, new int[]{2, 3});
        System.out.println(ddd3);


        INDArray abs = Transforms.abs(ddd3);
        System.out.println(abs);

        System.out.println(ddd3);


    }
}
