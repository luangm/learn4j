package io.luan.learn4j.test.basics;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.Session;
import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.*;
import io.luan.learn4j.optimizer.GradientDescentOptimizer;
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

        Parameter b = Learn4j.parameter("b", Nd4j.ones(1, 1));
        Parameter W = Learn4j.parameter("W", Nd4j.ones(1, 1).add(5));
        Parameter x = Learn4j.parameter("x", Nd4j.ones(1, 1).add(2));
        Parameter y = Learn4j.parameter("y", Nd4j.ones(1, 1));

        Multiply mul = Learn4j.mul("mul", W, x);
        Add add = Learn4j.add("add", mul, b);
        Subtract error = new Subtract("sub", add, y);
        Square square = new Square("square", error);

        ReduceMean loss = new ReduceMean("mean", square, 0);

        Graph graph = new GraphImpl();
        graph.add(loss);

        System.out.println(graph);

        GradientDescentOptimizer optimizer = new GradientDescentOptimizer(graph, 0.01);

        graph.gradient(loss, "b");
        graph.gradient(loss, "W");


        Session sess = new Session(graph);

        for (int i = 0; i < 1000; i++) {
            Map<String, INDArray> feedDict = new HashMap<String, INDArray>();
            feedDict.put("x", Nd4j.ones(1, 1).add(2));
            feedDict.put("y", Nd4j.ones(1, 1).add(17));
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
