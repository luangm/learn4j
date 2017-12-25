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
public class Test2 {

    @Test
    public void testMatrixGradientDescent() throws IOException {

        var W = parameter("W1", create(new double[]{1, 1}, new int[]{1, 2}));
        var b = parameter("b1", create(new double[]{1}, new int[]{1, 1}));
        var x = variable("x", new int[]{2, 1});

        var mmul = matmul(W, x);
        var add = add(mmul, b);
        var abs = abs(add);

        var gd = gradientDescentOptimizer(0.1);
        var train = gd.minimize(abs);

        Map<Expression, Tensor> feed = new HashMap<Expression, Tensor>();
        feed.put(x, create(new double[]{1, 1}, new int[]{2, 1}));

        Session sess = session("My Session");
        println("W1: " + sess.run(W, feed));
        println("x: " + sess.run(x, feed));
        println("b1: " + sess.run(b, feed));
        println("mmul: " + sess.run(mmul, feed));
        println("add: " + sess.run(add, feed));
        println("abs: " + sess.run(abs, feed));

        long now = new Date().getTime();
        val runtime = Runtime.getRuntime();

        for (int i = 0; i < 10000; i++) {
            sess.run(train, feed);
            println(sess.run(abs, feed));
//            println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
//            if (i % 5000 == 0) {
//                runtime.gc();
//            }
        }

        println("W1: " + sess.run(W, feed));
        println("b1: " + sess.run(b, feed));

        long now2 = new Date().getTime();


        System.out.println("Finished in: " + (now2 - now) + " ms");
        println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
        runtime.gc();
        println((runtime.totalMemory() - runtime.freeMemory()) / 1000000);
    }


    @Test
    public void testNd4j() throws IOException {
        var W1 = Nd4j.create(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        var b1 = Nd4j.create(new double[]{6, 5}, new int[]{2, 1});
        var x = Nd4j.create(new double[]{2, 3, 1, 2, 3, 1, 3, 1, 2, 2, 3, 1}, new int[]{3, 4});
        var y = Nd4j.create(new double[]{3, 4, 2, 4, 4, 3, 4, 3}, new int[]{2, 4});

        var mmul = Nd4j.zeros(2, 4);
        var xT = x.transpose();
        var dW = Nd4j.zeros(2, 3);
        var dB = Nd4j.zeros(2, 4);

        long now = new Date().getTime();
        val runtime = Runtime.getRuntime();

        for (int i = 0; i < 100000; i++) {
//            var b2 = b1.broadcast(2, 4);
            mmul = W1.mmul(x, mmul);
            mmul.addiColumnVector(b1);
//            mmul.addi(b2);
            mmul.subi(y);
            mmul.muli(0.00002);

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
