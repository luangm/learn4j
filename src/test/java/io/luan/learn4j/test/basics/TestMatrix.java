package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestMatrix {

    @Test
    public void testMatrixGradientDescent() {

        var W1 = parameter("W1", create(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3}));
        var b1 = parameter("b1", create(new double[]{6, 5}, new int[]{2, 1}));
        var x = variable("x", new int[]{3, 1});
        var y = variable("y", new int[]{2, 1});

        var mmul = matmul(W1, x);
        Expression gradient = mmul.getGradient(W1);
        println(gradient);

        var add = add(mmul, b1);
        var sub = subtract(add, y);
        var square = square(sub);
        var mean = reduceMean(square);

        var loss = mean;

        var gd = gradientDescentOptimizer(0.01);
        var train = gd.minimize(loss);

        Map<Expression, Tensor> feed = new HashMap<Expression, Tensor>();
        feed.put(x, create(new double[]{2, 3, 1}, new int[]{3, 1}));
        feed.put(y, create(new double[]{3, 4}, new int[]{2, 1}));

        Session sess = session("My Session");
        println("W1: " + sess.run(W1, feed));
        println("x: " + sess.run(x, feed));
        println("b1: " + sess.run(b1, feed));
        println("mmul: " + sess.run(mmul, feed));
        println("add: " + sess.run(add, feed));
        println("sub: " + sess.run(sub, feed));
        println("square: " + sess.run(square, feed));
        println("mean: " + sess.run(mean, feed));

        sess.run(train, feed);
//        for (int i = 0; i < 100; i++) {


//            println("W1_Train: " + sess.run(train, feed));
//        }
    }


}
