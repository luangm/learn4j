package io.luan.learn4j.test.opTest;

import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GroupTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        val y = constant(new double[]{2, 3, 4, 5, 6, 7}, new int[]{2, 3});
        val add = add(x, y);
        val sub = subtract(x, y);
        val group = group(add, sub);
        group.eval();
        println(group);
    }

}
