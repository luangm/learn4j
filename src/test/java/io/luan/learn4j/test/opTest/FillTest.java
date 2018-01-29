package io.luan.learn4j.test.opTest;

import io.luan.learn4j.Learn4j;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class FillTest {

    @Test
    public void testEval() {
        val fill = Learn4j.fill(1, new int[]{2, 3});
        fill.eval();
        println(fill);
    }

}
