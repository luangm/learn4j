package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.visitor.impl.ForwardGradientVisitor;
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

        val a = parameter("a", scalar(5));
        val b = parameter("b", scalar(3));
        val c = parameter("c", scalar(4));

        val add = add(a, b);
        val mul = multiply(add, c);

        val sess = session("My Session");

        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("c: " + sess.run(c));
        println("add: " + sess.run(add));
        println("mul: " + sess.run(mul));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        mul.accept(visitor);

        println(sess.getGraph());
//        println(add.getGradients());
    }
}
