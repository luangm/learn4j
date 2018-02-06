package io.luan.learn4j.test.mnist;

import io.luan.learn4j.mnist.MnistDataset;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.io.IOException;
import java.util.Date;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MnistWithNd4jTest {

    @Test
    public void testRaw() throws IOException {

        MnistDataset dataSet = new MnistDataset();
        INDArray x = dataSet.getData(10).reshape(10, 784).getArray();
        INDArray y = dataSet.getLabels(10).getArray();

        Date now = new Date();

        INDArray W = Nd4j.ones(784, 10);
        INDArray b = Nd4j.ones(1, 10);

        INDArray fill = Nd4j.ones(10, 10);

        INDArray mm = Nd4j.create(10, 10);
        for (int i = 0; i < 100000; i++) {

            x.mmul(W, mm);
            INDArray yHat = mm.addi(b.broadcast(10, 10));
            INDArray softmax = Transforms.softmax(yHat, false);
            INDArray sub = softmax.subi(y);
            INDArray yHatGrad = sub.muli(fill);

            INDArray dLdB = yHatGrad.sum(0).reshape(1, 10);
            INDArray dLdW = Nd4j.gemm(x, yHatGrad, true, false);

            INDArray dW = dLdW.muli(0.001);
            W.subi(dW);

            INDArray dB = dLdB.muli(0.001);
            b.subi(dB);
        }
        Date then = new Date();
//        println(W);
        println(b);
        println("Time Taken: " + (then.getTime() - now.getTime()) + "ms");
//        var mm = matmul(x, W);
//        var yHat = add(mm, b);
//        var xen = softmaxCrossEntropy(yHat, y);
//        var loss = reduceSum(xen);
//
////        var trainStep = optimizer.minimize(loss);
//
//        var grads = gradients(loss, new Expression[]{W, b});
//var w_grad = grads[0];
//var b_grad = grads[1];
//var lr = parameter(Tensor.scalar(0.001), "lr");
//
//var w_mul = multiply(lr, w_grad);
//var b_mul =  multiply(lr, b_grad);
//
//        Date now = new Date();
//
////        INDArray result = Nd4j.create(10, 10);
////        INDArray left = data.getArray();
////        INDArray right = W.getValue().getArray();
//
//        for (int i = 0; i < 10000; i++){
//
//            Tensor w_new = w_mul.eval();
//            Tensor b_new = b_mul.eval();
//            W.getValue().getArray().subi(w_new.getArray());
//            W.setState(ExpressionState.Modified);
//            W.notifyValueChanged();
//            b.getValue().getArray().subi(b_new.getArray());
//            b.setState(ExpressionState.Modified);
//            b.notifyValueChanged();
//
////            b_mul.eval();
////            b_mul.eval();
////            val grad = loss.getGradient(exp);
////            val mul = factory.multiply(this.learnRate, grad);
////            val sub = factory.subtract(exp, mul);
//
////            grad.eval();
////            println(yHat.eval());
////            println(xen.eval());
////println(grad.eval());
//
////            Tensor result = loss.eval();
////            println(result);
////            trainStep.eval();
////            println(b.eval());
////            println("------");
//        }
//
//
//
////        println(W);
//        println(b);
//
//        println("Time Taken: " + (then.getTime() - now.getTime()) + "ms");
    }

}
