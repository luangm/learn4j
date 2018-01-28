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
public class TestRelu {

    @Test
    public void testRelu() {

        val a = parameter( create(new double[]{-2, -1.2, -0.4, 0.4, 1.2, 2}, new int[]{2, 3}), "a");
        val relu = relu(a);
        val sess = session("My Session");
        println("a: " + sess.eval(a));
        println("relu: " + sess.eval(relu));

        ReverseGradientVisitor visitor = new ReverseGradientVisitor();
        relu.accept(visitor);

        val gradA = a.getGradient(relu);
        println("gradA: " + sess.eval(gradA));

        println(sess.getGraph());
    }

    @Test
    public void testStep() {
        val a = parameter(create(new double[]{-2, -1.2, -0.4, 0.4, 1.2, 2}, new int[]{2, 3}), "a");
        val step = step(a);
        val sess = session("My Session");
        println("a: " + sess.eval(a));
        println("step: " + sess.eval(step));
    }
}
