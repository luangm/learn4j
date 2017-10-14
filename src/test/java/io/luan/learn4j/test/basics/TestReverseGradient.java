package io.luan.learn4j.test.basics;

import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.scalar;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestReverseGradient {

    @Test
    public void testGradientDescent() {

        val a = parameter("a", scalar(3));
        val b = parameter("b", scalar(4));
        val c = parameter("c", scalar(5));

        val add = add(a, b);
        val mul = multiply(add, c);
        val square = square(a);
        val sub = subtract(a, b);

        val sess = session("My Session");

        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("c: " + sess.run(c));
        println("add: " + sess.run(add));
        println("mul: " + sess.run(mul));
        println("square: " + sess.run(square));
        println("sub: " + sess.run(sub));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        sub.accept(visitor);

        val grad = a.getGradient(sub);
        println("grad: " + sess.run(grad));
//        println(add.getGradients());
        println(sess.getGraph());
    }
}
