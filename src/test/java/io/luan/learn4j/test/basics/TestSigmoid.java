package io.luan.learn4j.test.basics;

import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestSigmoid {

    @Test
    public void testSigmoid() {

        val a = parameter(create(new double[]{-2, -1.2, -0.4, 0.4, 1.2, 2}, new int[]{2, 3}), "A");

        val sigmoid = sigmoid(a);

        val sess = session("My Session");

        println("a: " + sess.eval(a));
        println("sigmoid: " + sess.eval(sigmoid));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        sigmoid.accept(visitor);

        val gradA = a.getGradient(sigmoid);
        println("gradA: " + sess.eval(gradA));

        println(sess.getGraph());
    }
}
