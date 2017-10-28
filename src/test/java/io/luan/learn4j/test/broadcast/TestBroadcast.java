package io.luan.learn4j.test.broadcast;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.experimental.var;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
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
public class TestBroadcast {

    @Test
    public void testAdd() throws IOException {

        var a = constant("a", create(new double[]{2, 5}, new int[]{1, 2}));
        var b = constant("b", create(new double[]{3, 7}, new int[]{2, 1}));
        var z = add(a, b);

        var visitor = new ReverseGradientVisitor();
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("z: " + sess.run(z));

        var da = a.getGradient();
        println("da: " + sess.run(da));

        var db = b.getGradient();
        println("db: " + sess.run(db));
    }

    @Test
    public void testSubtract() throws IOException {

        var a = constant("a", create(new double[]{2, 5}, new int[]{1, 2}));
        var b = constant("b", create(new double[]{3, 7}, new int[]{2, 1}));
        var z = subtract(a, b);

        var visitor = new ReverseGradientVisitor();
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("z: " + sess.run(z));

        var da = a.getGradient();
        println("da: " + sess.run(da));

        var db = b.getGradient();
        println("db: " + sess.run(db));
    }

    @Test
    public void testMultiply() throws IOException {

        var a = constant("a", create(new double[]{2, 5}, new int[]{1, 2}));
        var b = constant("b", create(new double[]{3, 7}, new int[]{2, 1}));
        var z = multiply(a, b);

        var visitor = new ReverseGradientVisitor();
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("z: " + sess.run(z));

        var da = a.getGradient();
        println("da: " + sess.run(da));

        var db = b.getGradient();
        println("db: " + sess.run(db));
    }

    @Test
    public void testDivide() throws IOException {

        var a = constant("a", create(new double[]{2, 5}, new int[]{1, 2}));
        var b = constant("b", create(new double[]{3, 7}, new int[]{2, 1}));
        var z = divide(a, b);

        var visitor = new ReverseGradientVisitor();
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.run(a));
        println("b: " + sess.run(b));
        println("z: " + sess.run(z));

        var da = a.getGradient();
        println("da: " + sess.run(da));

        var db = b.getGradient();
        println("db: " + sess.run(db));
    }

}
