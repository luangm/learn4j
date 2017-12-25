package io.luan.learn4j.test.basics;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestReverseGradient {

    @Test
    public void testGradientDescent() {

        val a = parameter("a", create(new double[]{.1, .2, .3, .4, .5, .6}, new int[]{2, 3}));
        val b = parameter("b", create(new double[]{.1, .2, .3, .4, .2, .3, .4, .5, .3, .4, .5, .6}, new int[]{3, 4}));
        val y = parameter("y", create(new double[]{1, 0, 1, 0, 0, 1, 0, 1}, new int[]{2, 4}));

        val matmul = matmul(a, b);
//        val sigmoid = sigmoid(matmul);
//        val sub = subtract(y, sigmoid);
//        val square = square(sub);
//        val reduceSum = reduceSum(square);

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

        val gradA = a.getGradient(matmul);
        println("gradA: " + sess.run(gradA));

        val gradB = b.getGradient(matmul);
        println("gradB: " + sess.run(gradB));

//        println(add.getGradients());
        println(sess.getGraph());
    }


}
