package io.luan.learn4j.test.basics;

import io.luan.learn4j.Session;
import io.luan.learn4j.Tensor;
import io.luan.learn4j.structure.Graph;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.Tensor.ones;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestConstant {

    @Test
    public void testConstantOp() {

        Graph graph = graph("My First Graph");

        val a = constant("a", ones(1, 1));
        val b = constant("b", ones(1, 1));

        val add2 = add(a, b);

        Session sess = session("My Session");

        Tensor result = sess.run(add2);
        System.out.println(result);
    }
}
