package io.luan.learn4j.test.broadcast;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.visitor.impl.ReverseGradientVisitor;
import lombok.experimental.var;
import org.junit.Test;

import java.io.IOException;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.create;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestBroadcast {

    @Test
    public void testAdd() throws IOException {

        var a = constant(new double[]{2, 5}, new int[]{1, 2});
        var b = constant(new double[]{3, 7}, new int[]{2, 1});
        var z = add(a, b);

        var visitor = new ReverseGradientVisitor(null);
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.eval(a));
        println("b: " + sess.eval(b));
        println("z: " + sess.eval(z));

        var da = a.getGradient();
        println("da: " + sess.eval(da));

        var db = b.getGradient();
        println("db: " + sess.eval(db));
    }

    @Test
    public void testSubtract() throws IOException {

        var a = constant(new double[]{2, 5}, new int[]{1, 2});
        var b = constant(new double[]{3, 7}, new int[]{2, 1});
        var z = subtract(a, b);

        var visitor = new ReverseGradientVisitor(null);
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.eval(a));
        println("b: " + sess.eval(b));
        println("z: " + sess.eval(z));

        var da = a.getGradient();
        println("da: " + sess.eval(da));

        var db = b.getGradient();
        println("db: " + sess.eval(db));
    }

    @Test
    public void testMultiply() throws IOException {

        var a = constant(new double[]{2, 5}, new int[]{1, 2});
        var b = constant(new double[]{3, 7}, new int[]{2, 1});
        var z = multiply(a, b);

        var visitor = new ReverseGradientVisitor(null);
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.eval(a));
        println("b: " + sess.eval(b));
        println("z: " + sess.eval(z));

        var da = a.getGradient();
        println("da: " + sess.eval(da));

        var db = b.getGradient();
        println("db: " + sess.eval(db));
    }

    @Test
    public void testDivide() throws IOException {

        double[] x = {1,2,3,4,5};
        double[][] y = {{1,2,3},{4,5,6}};

        var a = constant(new double[]{2, 5}, new int[]{1, 2});
        var b = constant(new double[]{3, 7}, new int[]{2, 1});
        var z = divide(a, b);

        var visitor = new ReverseGradientVisitor(null);
        z.accept(visitor);

        Session sess = session("My Session");
        println("a: " + sess.eval(a));
        println("b: " + sess.eval(b));
        println("z: " + sess.eval(z));

        var da = a.getGradient();
        println("da: " + sess.eval(da));

        var db = b.getGradient();
        println("db: " + sess.eval(db));
    }

}
