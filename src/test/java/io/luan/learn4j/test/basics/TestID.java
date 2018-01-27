package io.luan.learn4j.test.basics;

import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.scalar;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestID {

    @Test
    public void testId() {

        val W1 = parameter(scalar(5));
        val W2 = parameter(scalar(5));
        println(W1);
        println(W2);
    }
}
