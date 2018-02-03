package io.luan.learn4j.test.opTest;

import io.luan.learn4j.Learn4j;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MinTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        println(x);

        val result = reduceMin(x, 1);
        result.eval();
        println(result);
    }

}
