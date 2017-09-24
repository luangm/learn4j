package io.luan.learn4j.test.tensor;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.Session;
import io.luan.learn4j.Tensor;
import io.luan.learn4j.expression.*;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class BasicTest {

    @Test
    public void testCreation() {

        Tensor one = Tensor.ones(2, 3);
        System.out.println(one);

        Tensor rand = Tensor.rand(3, 2);
        System.out.println(rand);

        Tensor scalar = Tensor.scalars(5, 3, 2);
        System.out.println(scalar);

    }
}
