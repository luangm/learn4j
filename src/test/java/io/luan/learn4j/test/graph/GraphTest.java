package io.luan.learn4j.test.graph;

import io.luan.learn4j.Learn4j;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.fill;
import static io.luan.learn4j.core.Tensor.ones;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphTest {

    @Test
    public void testCreate() {
        val x = constant(ones(2, 3));
        val y = constant(fill(5, 1, 3));
        println(x);
        println(y);

        val neg = negate(x);
        println(neg);

        for (int i = 0; i < 2; i++) {
            val neg2 = negate(x);
            neg2.eval();
            println(neg2);
            val sum = add(x, y);
            sum.eval();
            println(sum);
        }

        val z = constant(ones(1, 1));
        println(z);

    }

}
