package io.luan.learn4j.test.opTest;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.transform.Exponential;
import lombok.experimental.var;
import lombok.val;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class AddTest {

    @Test
    public void testEval() {
        val x = constant(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        val y = constant(new double[]{2, 3, 4, 5, 6, 7}, new int[]{2, 3});
        val add = add(x, y);
        add.eval();
        println(add);
    }

    @Test
    public void testGrad() {
        val x = constant(new double[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        val y = constant(new double[]{2, 3, 4, 5, 6, 7}, new int[]{2, 3});
        val add = add(x, y);
        add.eval();
        println(add);

        var initialGrad = constant(new double[]{2, 2, 2, 2, 2, 2}, new int[]{2, 3});

        val result = Learn4j.gradients(add, new Expression[] {x, y}, initialGrad);
        val x_grad = result[0];
        val y_grad = result[1];
        x_grad.eval();
        y_grad.eval();
        println(x_grad);
        println(y_grad);

    }

    @Test
    public void testBroadcastGrad() {
        val x = constant(new double[]{1, 2, 3}, new int[]{1, 3});
        val y = constant(new double[]{4, 5, 6}, new int[]{3, 1});
        val add = add(x, y);
        add.eval();
        println(add);

        var initialGrad = constant(new double[]{2, 2, 2, 2, 2, 2}, new int[]{2, 3});

        val result = Learn4j.gradients(add, new Expression[] {x, y});
        val x_grad = result[0];
        val y_grad = result[1];
        x_grad.eval();
        y_grad.eval();
        println(x_grad);
        println(y_grad);

    }
}
