package io.luan.learn4j.test.mnist;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.mnist.MnistDataset;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionState;
import lombok.experimental.var;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static io.luan.learn4j.Learn4j.*;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MnistTest {

    @Test
    public void test2() throws IOException {
        int x = 126;
        float y = x;
        y = y * (1 / 255.0f);
        System.out.println(y);
    }

    @Test
    public void testLoader() throws IOException {

        MnistDataset dataSet = new MnistDataset();
        Tensor data = dataSet.getData(10).reshape(10, 784);
//        System.out.println(data);

        Tensor labels = dataSet.getLabels(10);
//        System.out.println(labels);

        var x = variable(new int[]{10, 784}, "x");
        x.setValue(data);
        var y = variable(new int[]{10, 10}, "y");
        y.setValue(labels);

        var W = parameter(Tensor.ones(784, 10), "W");
        var b = parameter(Tensor.ones(1, 10), "b");

        var optimizer = Learn4j.gradientDescentOptimizer(0.001);

        var mm = matmul(x, W);
        var yHat = add(mm, b);
        var xen = softmaxCrossEntropy(yHat, y);
        var loss = reduceSum(xen);

//        var trainStep = optimizer.minimize(loss);

        var grads = gradients(loss, new Expression[]{W, b});
        var w_grad = grads[0];
        var b_grad = grads[1];
        var lr = constant(Tensor.scalar(0.001), "lr");

        var w_mul = subtract(W, multiply(lr, w_grad));
        var b_mul = subtract(b, multiply(lr, b_grad));

        Date now = new Date();

//        INDArray result = Nd4j.create(10, 10);
//        INDArray left = data.getArray();
//        INDArray right = W.getValue().getArray();

        for (int i = 0; i < 100000; i++) {

            Tensor w_new = w_mul.eval();
            Tensor b_new = b_mul.eval();
            W.setValue(w_new);
            b.setValue(b_new);

//            b_mul.eval();
//            b_mul.eval();
//            val grad = loss.getGradient(exp);
//            val mul = factory.multiply(this.learnRate, grad);
//            val sub = factory.subtract(exp, mul);

//            grad.eval();
//            println(yHat.eval());
//            println(xen.eval());
//println(grad.eval());

//            Tensor result = loss.eval();
//            println(result);
//            trainStep.eval();
//            println(b.eval());
//            println("------");
        }

        Date then = new Date();

//        println(W);
        println(b);

        println("Time Taken: " + (then.getTime() - now.getTime()) + "ms");
    }

}
