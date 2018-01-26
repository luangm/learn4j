package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.factory.Nd4j;

import java.io.IOException;
import java.util.Date;
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
    public void testMatrixGradientDescent() throws IOException {

        var W1 = parameter("W1", create(new double[]{.1, .2, .3, .4, .5, .6}, new int[]{2, 3}));
        var b1 = parameter("b1", create(new double[]{.6, .5}, new int[]{2, 1}));
        var x = variable("x", new int[]{3, 2});
        var y = variable("y", new int[]{2, 2});

        var mmul = matmul(W1, x);
        var add = add(mmul, b1);
        var sigmoid = sigmoid(add);
        var sub = subtract(y, sigmoid);
        var square = square(sub);
        var loss = reduceSum(square);

        var gd = gradientDescentOptimizer(0.1);
        var train = gd.minimize(loss);

        Map<Expression, Tensor> feed = new HashMap<Expression, Tensor>();
        feed.put(x, create(new double[]{0.2, 0.3, 0.3, 0.4, 0.1, 0.1}, new int[]{3, 2}));
        feed.put(y, create(new double[]{0.3, 0.4, 0.4, 0.5}, new int[]{2, 2}));

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
        val runtime = Runtime.getRuntime();

        for (int i = 0; i < 100000; i++) {
            sess.run(train, feed);
//            println(sess.run(loss, feed));
//            println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
//            if (i % 5000 == 0) {
//                runtime.gc();
//            }
        }

        println("W1: " + sess.run(W1, feed));
        println("b1: " + sess.run(b1, feed));
        println("loss: " + sess.run(loss, feed));

        long now2 = new Date().getTime();


        System.out.println("Finished in: " + (now2 - now) + " ms");
        println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
        runtime.gc();
        println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
    }


    @Test
    public void testNd4j() throws IOException {
        var W1 = Nd4j.create(new double[]{.1, .2, .3, .4, .5, .6}, new int[]{2, 3});
        var b1 = Nd4j.create(new double[]{.6, .5}, new int[]{2, 1});
        var x = Nd4j.create(new double[]{.2, .3, .3, .4 , .1 , .1}, new int[]{3, 2});
        var y = Nd4j.create(new double[]{.3, .4, .4, .5}, new int[]{2, 2});

        var mmul = Nd4j.zeros(2, 2);
        var xT = x.transpose();
        var dW = Nd4j.zeros(2, 3);
        var dB = Nd4j.zeros(2, 2);

        long now = new Date().getTime();
        val runtime = Runtime.getRuntime();

        for (int i = 0; i < 100000; i++) {
//            var b2 = b1.broadcast(2, 4);
            mmul = W1.mmul(x, mmul);
            mmul.addiColumnVector(b1);
//            mmul.addi(b2);
            mmul.subi(y);
            mmul.muli(0.2);

            dW = mmul.mmul(xT);
            dB = mmul;

            W1.subi(dW);
            b1.subi(dB.getColumn(0));
        }

        System.out.println("W1: " + W1);
        System.out.println("b1: " + b1);

        long now2 = new Date().getTime();

        System.out.println("Finished in: " + (now2 - now) + " ms");

    }
}
