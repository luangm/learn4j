package io.luan.learn4j.test.basics;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.Session;
import io.luan.learn4j.Tensor;
import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.*;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class BasicTest {

    @Test
    public void testAnd() {

        val b = Learn4j.parameter("b", Tensor.ones(1, 1));
        val W = Learn4j.parameter("W", Tensor.ones(1, 1));
        val x = Learn4j.parameter("x", Tensor.ones(1, 1));
        val y = Learn4j.parameter("y", Tensor.ones(1, 1));

        val mul = Learn4j.mul("mul", W, x);
        val add = Learn4j.add("add", mul, b);
        val error = new Subtract("sub", add, y);
        val square = new Square("square", error);

        val loss = new ReduceMean("mean", square, 0);

        Graph graph = new GraphImpl();
        graph.add(loss);

        System.out.println(graph);

        GradientDescentOptimizer optimizer = new GradientDescentOptimizer(graph, 0.01);

        graph.gradient(loss, "b");
        graph.gradient(loss, "W");

        Map<String, INDArray> feedDict = new HashMap<String, INDArray>();
        feedDict.put("x", Nd4j.ones(1, 1).add(2));
        feedDict.put("y", Nd4j.ones(1, 1).add(17));

        Session sess = new Session(graph);

        for (int i = 0; i < 1000; i++) {

//
            sess.run(feedDict);

//            System.out.println(b);
//            System.out.println(W);
//            System.out.println(x);
//            System.out.println(mul);
//            System.out.println(add);
//            System.out.println(error);
//            System.out.println(square);
//            System.out.println(loss);

            optimizer.minimize(loss);
        }
    }
}
