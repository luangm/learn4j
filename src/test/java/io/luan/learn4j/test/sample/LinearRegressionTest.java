package io.luan.learn4j.test.sample;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import lombok.experimental.var;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class LinearRegressionTest {

    @Test
    public void testLinearRegression() throws IOException {

        var W1 = parameter(create(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}));
        var b1 = parameter(create(new double[]{6, 5}, new int[]{2, 1}));
        var x = variable(new int[]{3, 3});
        var y = variable(new int[]{2, 3});

        var mmul = matmul(W1, x);
        var add = add(mmul, b1);
        var sub = subtract(y, add);
        var square = square(sub);
        var loss = reduceSum(square);

        var gd = gradientDescentOptimizer(0.00001);
        var train = gd.minimize(loss);

        Map<Expression, Tensor> feed = new HashMap<Expression, Tensor>();
        feed.put(x, create(new double[]{2, 3, 1, 2, 3, 1, 3, 1, 2}, new int[]{3, 3}));
        feed.put(y, create(new double[]{3, 4, 2, 4, 4, 3}, new int[]{2, 3}));

        Session sess = session("My Session");
        println("W1: " + sess.run(W1, feed));
        println("x: " + sess.run(x, feed));
        println("b1: " + sess.run(b1, feed));
        println("mmul: " + sess.run(mmul, feed));
        println("add: " + sess.run(add, feed));
        println("sub: " + sess.run(sub, feed));
        println("square: " + sess.run(square, feed));
        println("loss: " + sess.run(loss, feed));

        long now = new Date().getTime();

        for (int i = 0; i < 100000; i++) {
            sess.run(train, feed);
//            sess.run(loss, feed);
//            println("loss: " + sess.run(loss, feed));
        }
        println("W1: " + sess.run(W1, feed));
        println("b1: " + sess.run(b1, feed));

        long now2 = new Date().getTime();
        System.out.println("Finished in: " + (now2 - now) + " ms");

    }

}
