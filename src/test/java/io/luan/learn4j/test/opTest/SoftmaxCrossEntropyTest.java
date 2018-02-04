package io.luan.learn4j.test.opTest;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class SoftmaxCrossEntropyTest {

    @Test
    public void testEval() {
        val logits = constant(new double[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{2, 4});
        val yTrue = constant(new double[]{0, 0, 0, 1, 1, 0, 0, 0}, new int[]{2,4});

        val result = softmaxCrossEntropy(logits, yTrue);
        result.eval();
        println(result);
    }

    @Test
    public void testGrad() {

//        val x = constant(new double[]{-1, 1, -2, 2, -3, 3}, new int[]{2, 3});
//        val abs = abs(x);
//        abs.eval();
//        println(abs);
//
//        var initialGrad = constant(new double[]{2, 2, 2, 2, 2, 2}, new int[]{2, 3});
//
//        val result = Learn4j.gradients(abs, x, initialGrad);
//        result.eval();
//        println(result);

    }

}
