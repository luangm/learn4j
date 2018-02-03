package io.luan.learn4j.test.graph;

import io.luan.learn4j.core.Tensor;
import lombok.experimental.var;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;
import static io.luan.learn4j.core.Tensor.ones;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphTest {

    @Test
    public void testCreate() {

        var W = parameter(ones(2, 3), "W");
        var x = variable(new int[]{3, 2});
        var b = parameter(ones(2, 1), "b");
        println(W);
        println(x);

        var lr = constant(new double[]{0.05}, new int[]{1, 1});

        for (int i = 0; i < 4; i++) {
            x.setValue(Tensor.fill(3, 3, 2));
//            println(x);

            var mm = matmul(W, x);
            var yHat = add(mm, b);

//            yHat.eval();
//            println(yHat);

            var W_grad = gradients(yHat, W);
            var diff = multiply(lr, W_grad);
            var newVal = subtract(W_grad, diff);
            newVal.eval();
            println(newVal);

            W.setValue(newVal.getValue());
            println(W);
        }

    }

}
