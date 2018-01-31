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
public class SumTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, new int[]{2, 2, 3});
        println(x);

        val result = reduceSum(x, 0);
        result.eval();
        println(result);
    }


    @Test
    public void testGrad() {
        val x = constant(new double[]{1,2,3,4,5,6}, new int[]{2, 3});
        val abs = reduceSum(x);
        abs.eval();
        println(abs);

        val result = Learn4j.gradients(abs, x);
        result.eval();
        println(result);

    }
}
