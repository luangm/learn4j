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

        val add = add(a, b);

//        val gd = gradientDescentOptimizer(0.01);
//        val train = gd.minimize(loss2);

        Session sess = session("My Session");

        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("add: " + sess.run(add));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        add.accept(visitor);

        println(add);
//        println(add.getGradients());
    }
}
