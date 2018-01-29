package io.luan.learn4j.test.opTest;

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

}
