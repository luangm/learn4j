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
public class NegTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{-1, 2, -3, 4, -5, 6}, new int[]{2, 3});
        val neg = negate(x);
        neg.eval();
        println(neg);
    }

    @Test
    public void testGrad() {
        val x = constant(new double[]{-1, 1, -2, 2, -3, 3}, new int[]{2, 3});
        val abs = negate(x);
        abs.eval();
        println(abs);

//        var initialGrad = constant(new double[]{2, 2, 2, 2, 2, 2}, new int[]{2, 3});

        val result = Learn4j.gradients(abs, x);
        result.eval();
        println(result);

    }
}
