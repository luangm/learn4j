package io.luan.learn4j.test.nd4j;

import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestEq {

    @Test
    public void testMin() {
        val x = Nd4j.linspace(1, 6, 6).reshape(2, 3);
        println(x);

        val y = Nd4j.linspace(2, 6, 6).reshape(2, 3);
        println(y);

        val result = Nd4j.min(x).broadcast(x.shape());
        println(result);

        val result2 = x.sub(result).eq(0);
        println(result2);

    }

}
