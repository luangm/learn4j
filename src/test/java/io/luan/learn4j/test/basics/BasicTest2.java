package io.luan.learn4j.test.basics;

import io.luan.learn4j.Session;
import io.luan.learn4j.Tensor;
import io.luan.learn4j.expression.Graph;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.Tensor.ones;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class BasicTest2 {

    @Test
    public void testNew() {

        Graph graph = graph("My First Graph");

        val W = parameter("W", ones(1, 1));
        val b = parameter("b", ones(1, 1));
        val x = variable("x", new int[]{1, 1});
        val y = variable("y", new int[]{1, 1});

        val mul = mul("mul", W, x);
        val add = add("add", mul, b);
        val error = sub("sub", add, y);
        val square = square("square", error);
        val loss = reduceMean("mean", square);

        val loss2 = reduceMean(square(sub(add(mul(W, x), b), y)));

        val gd = gradientDescentOptimizer(0.01);
        val train_step = gd.minimize(loss);

        Session sess = session("My Session");

        val out = sess.run(loss, train_step);
        System.out.println(out);
    }
}
