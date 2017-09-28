package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.Graph;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.ones;

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

        val add2 = add(W, b);
        val mul = multiply("mul", W, x);
        val add = add("add", mul, b);
        val error = subtract("sub", add, y);
        val square = square("square", error);
        val loss = reduceMean("mean", square);

        val loss2 = reduceMean(square(subtract(add(multiply(W, x), b), y)));

        val gd = gradientDescentOptimizer(0.01);
//        val train_step = gd.minimize(loss);

        Session sess = session("My Session");

        Tensor out = sess.run(mul);
        System.out.println(out);
    }
}
