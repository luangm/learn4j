package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import java.io.IOException;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestReverseGradient {

    @Test
    public void testGradientDescent() {

        val a = parameter( create(new double[]{.1, .2, .3, .4, .5, .6}, new int[]{2, 3}));
        val b = parameter( create(new double[]{.1, .2, .3, .4, .2, .3, .4, .5, .3, .4, .5, .6}, new int[]{3, 4}));
        val y = parameter( create(new double[]{1, 0, 1, 0, 0, 1, 0, 1}, new int[]{2, 4}));

        val matmul = matmul(a, b);
        val sigmoid = sigmoid(matmul);
        val sub = subtract(y, sigmoid);
        val square = square(sub);
        val loss = reduceSum(square);

        val sess = session("My Session");

        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("y: " + sess.run(y));
        println("matmul: " + sess.run(matmul));
//        println("sigmoid: " + sess.run(sigmoid));
//        println("sub: " + sess.run(sub));
//        println("square: " + sess.run(square));
//        println("reduceSum: " + sess.run(reduceSum));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        matmul.accept(visitor);

        val gradA = a.getGradient(loss);
        println("gradA: " + sess.run(gradA));

        val gradB = b.getGradient(matmul);
        println("gradB: " + sess.run(gradB));

//        println(add.getGradients());
        println(sess.getGraph());
    }

    @Test
    public void testMatrixGradientDescent() throws IOException {

        var W1 = parameter(create(new double[]{.1, .2, .3, .4, .5, .6}, new int[]{2, 3}));
        var b1 = parameter(create(new double[]{.6, .5}, new int[]{2, 1}));
        var x = parameter(create(new double[]{0.2, 0.3, 0.3, 0.4, 0.1, 0.1}, new int[]{3, 2}));
        var y = parameter( create(new double[]{0.3, 0.4, 0.4, 0.5}, new int[]{2, 2}));

        var mmul = matmul(W1, x);
        var add = add(mmul, b1);
        var sigmoid = sigmoid(add);
        var sub = subtract(y, sigmoid);
        var square = square(sub);
        var loss = reduceSum(square);

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        loss.accept(visitor);

        Session sess = session("My Session");
        println("loss: " + sess.run(loss));

        var gradA = W1.getGradient();

        println("gradA: " + sess.run(gradA));

        var gradB = b1.getGradient();

        println("gradB: " + sess.run(gradB));
    }


}
