package io.luan.learn4j.test.basics;

import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.structure.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestSigmoid {

    @Test
    public void testSigmoid() {

        val a = parameter("a", create(new double[]{-2, -1.2, -0.4, 0.4, 1.2, 2}, new int[]{2, 3}));

        val sigmoid = sigmoid(a);

        val sess = session("My Session");

        println("a: " + sess.run(a));
        println("sigmoid: " + sess.run(sigmoid));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        sigmoid.accept(visitor);

        val gradA = a.getGradient(sigmoid);
        println("gradA: " + sess.run(gradA));

        println(sess.getGraph());
    }
}
