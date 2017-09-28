package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Tensor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.create;
import static io.luan.learn4j.structure.Tensor.scalar;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestConstant {

    @Test
    public void testConstantOp() {

        val a = constant("a", create(new double[]{1, 2, 3}));
        val b = constant("b", scalar(3));
        val c = parameter("c", scalar(5));
        val d = parameter("d", create(new double[]{4, 5, 6}));

        val add = add(a, b);
        val sub = subtract(add, b);
        val mul = multiply(a, c);
        val mul2 = multiply(a, d);

        Session sess = session("My Session");

        Tensor result = sess.run(add);
        System.out.println(result);

        Tensor result2 = sess.run(sub);
        System.out.println(result2);

        Tensor result3 = sess.run(mul);
        System.out.println(result3);

        Tensor result4 = sess.run(mul2);
        System.out.println(result4);
    }
}
