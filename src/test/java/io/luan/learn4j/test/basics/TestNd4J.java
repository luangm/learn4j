package io.luan.learn4j.test.basics;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.Add;
import io.luan.learn4j.expression.Graph;
import io.luan.learn4j.expression.MatMul;
import io.luan.learn4j.expression.Parameter;
import io.luan.learn4j.expression.visitor.GradientVisitor;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Set;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestNd4J {

    @Test
    public void testTranspose() {

        INDArray array = Nd4j.ones(4, 4);
        System.out.println(array);

        INDArray transposed = array.transpose();
        System.out.println(transposed);

        array.putScalar(1, 2, 5.0);
        System.out.println(array);

        System.out.println(transposed);
    }
}
