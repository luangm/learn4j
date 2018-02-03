package io.luan.learn4j.test.nd4j;

import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.factory.Nd4j;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MinTest {

    @Test
    public void testMin() {
        val array = Nd4j.linspace(1, 6, 6).reshape(2, 3);
        println(array);

        val min = Nd4j.min(array, 0);
        println(min);

        val argmin = Nd4j.argMax(array, 0);
        println(argmin);
    }

}
