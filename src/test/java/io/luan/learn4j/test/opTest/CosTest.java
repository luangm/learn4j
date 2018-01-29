package io.luan.learn4j.test.opTest;

import io.luan.learn4j.Learn4j;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class CosTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{0.1, 2, 0.2, 4, 0.3, 6}, new int[]{2, 3});
        val result = cos(x);
        result.eval();
        println(result);
    }


    @Test
    public void testGrad() {
        val x = constant(new double[]{-1, 1, -2, 2, -3, 3}, new int[]{2, 3});
        val result = cos(x);
        result.eval();
        println(result);

//        var initialGrad = constant(new double[]{2, 2, 2, 2, 2, 2}, new int[]{2, 3});

        val grad = Learn4j.gradients(result, x);
        grad.eval();
        println(grad);

    }

}
