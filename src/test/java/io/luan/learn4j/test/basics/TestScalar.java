package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.core.Tensor;
import lombok.val;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.scalar;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestScalar {

    @Test
    public void testGradientDescent() {

        val W1 = parameter(scalar(5), "a");
        val b1 = parameter(scalar(3));
        val x = variable( new int[]{1, 1});
        val y = variable(new int[]{1, 1});

        println(W1.getShape()[0]);
        println(W1.getShape()[1]);

        val loss2 = reduceMean(square(subtract(add(multiply(W1, x), b1), y)));

        val gd = gradientDescentOptimizer(0.01);
        val train = gd.minimize(loss2);

        Map<Expression, Tensor> feed = new HashMap<>();
        feed.put(x, scalar(4));
        feed.put(y, scalar(2));

        Session sess = session("My Session");

        for (int i = 0; i < 1; i++) {
            println("W1: " + sess.eval(W1, feed));
            println("b1: " + sess.eval(b1, feed));
            println("Loss: " + sess.eval(loss2, feed));
            println("W1_Train: " + sess.eval(train, feed));
        }
    }
}
