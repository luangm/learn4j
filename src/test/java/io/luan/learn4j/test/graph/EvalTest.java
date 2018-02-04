package io.luan.learn4j.test.graph;

import io.luan.learn4j.core.Tensor;
import lombok.experimental.var;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class EvalTest {

    @Test
    public void testCreate() {

        var x_data = Tensor.create(new double[]{0.2, 0.3, 0.3, 0.4, 0.1, 0.1}, new int[]{2, 3});
        var y_data = Tensor.create(new double[]{0.3, 0.4, 0.4, 0.5}, new int[]{2, 2});
        var W_data = Tensor.create(new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6}, new int[]{2, 3});
        var b_data = Tensor.create(new double[]{0.6, 0.5}, new int[]{2, 1});

        var x = variable(new int[]{2, 3}, "X");
        x.setValue(x_data);
//        var y = variable(new int[]{2, 2}, "Y");
        var W = parameter(W_data, "W");
        var b = parameter(b_data, "b");

        var ABC = add(W, b);
//        ABC.eval();

        var DEF = add(W, x);
//        DEF.eval();

        var XXX = add(ABC, DEF);
        var YYY = multiply(ABC, DEF);
        XXX.eval();
        YYY.eval();
    }

}
