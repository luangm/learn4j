package io.luan.learn4j.test.graph;

import io.luan.learn4j.core.Tensor;
import lombok.experimental.var;
import org.junit.Test;

import java.util.Date;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphTest {

    @Test
    public void testCreate() {

        Date now = new Date();
        var x_data = Tensor.create(new double[]{0.2, 0.3, 0.3, 0.4, 0.1, 0.1}, new int[]{3, 2});
        var y_data = Tensor.create(new double[]{0.3, 0.4, 0.4, 0.5}, new int[]{2, 2});
        var W_data = Tensor.create(new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, new int[]{2, 3});
        var b_data = Tensor.create(new double[]{0.6, 0.5}, new int[]{2, 1});

        var x = variable(new int[]{3, 2}, "X");
        var y = variable(new int[]{2, 2}, "Y");
        var W = parameter(W_data, "W");
        var b = parameter(b_data, "b");

        var yHat = sigmoid(add(matmul(W, x), b));
        var loss = reduceSum(square(subtract(y, yHat)));

        var optimizer = gradientDescentOptimizer(0.1);
        var train = optimizer.minimize(loss);

        Date beforeEval = new Date();

        x.setValue(x_data);
        y.setValue(y_data);

//        Expression[] gradients = gradients(loss, new Expression[]{W, b});
//        var w_grad = gradients[0];
//        var b_grad = gradients[1];
//
//        var lr = parameter(Tensor.scalar(0.1), "lr");
//        var w_mul = multiply(lr, w_grad);
//        var w_sub = subtract(W, w_mul);
//
//        var b_mul = multiply(lr, b_grad);
//        var b_sub = subtract(b, b_mul);
//
        for (int i = 0; i < 100000; i++) {
//            w_sub.eval();
//            b_sub.eval();
//
//            W.setValue(w_sub.getValue());
//            b.setValue(b_sub.getValue());
//            loss.eval();
//            println(loss);
            train.eval();
//            println(W);
//            println(b);
//            println("------------------");

        }

        Date after = new Date();
        println(W);
        println(b);
        println("Initial Step: " + (beforeEval.getTime() - now.getTime()));
        println("after Step: " + (after.getTime() - beforeEval.getTime()));
    }

}
