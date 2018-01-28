package io.luan.learn4j.test.graph;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.core.Tensor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphTest {

    @Test
    public void testCreate() {
        val x = Learn4j.constant(Tensor.ones(2, 3));
        val y = Learn4j.constant(Tensor.fill(5, 1, 3));

        val sum = Learn4j.add(x, y);
        sum.eval();

        println(x);
        println(y);
        println(sum);

    }

}
