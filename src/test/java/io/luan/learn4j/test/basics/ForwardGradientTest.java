package io.luan.learn4j.test.basics;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.visitor.impl.ForwardGradientVisitor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.scalar;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ForwardGradientTest {

    @Test
    public void testForward() {

        val a = parameter("a", scalar(5));
        val b = parameter("b", scalar(3));

        val add = add(a, b);

        Session sess = session("My Session");

        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("add: " + sess.run(add));

        ForwardGradientVisitor visitor = new ForwardGradientVisitor(a);
        add.accept(visitor);

        println(add);
    }
}
