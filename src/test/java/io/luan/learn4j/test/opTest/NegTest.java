package io.luan.learn4j.test.opTest;

import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class NegTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{-1, 2, -3, 4, -5, 6}, new int[]{2, 3});
        val neg = negate(x);
        neg.eval();
        println(neg);
    }

}
