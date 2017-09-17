package io.luan.learn4j.test.basics;

import io.luan.learn4j.Learn4j;
import io.luan.learn4j.Session;
import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.expression.*;
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
        Parameter c = Learn4j.parameter("c", Nd4j.ones(1, 1));

        Parameter W = Learn4j.parameter("W", Nd4j.rand(1, 1));
        Parameter x = Learn4j.parameter("x", Nd4j.ones(1, 1));

        Add add = Learn4j.add("add", c, b);

        Multiply mul = Learn4j.mul("mul", W, x);

        Graph graph = new GraphImpl();
        graph.add(add);
        graph.add(mul);

        System.out.println(graph);

        graph.gradient(add, "b");
        graph.gradient(add, "c");
        graph.gradient(mul, "W");
        graph.gradient(mul, "x");

        ComputeGraph computeGraph = graph.getComputeGraph();
        System.out.println(computeGraph);

        Session sess = new Session(graph);

        Map<String, INDArray> feedDict = new HashMap<String, INDArray>();
//        feedDict.put("x", Nd4j.rand(3, 1));
//        feedDict.put("y", Nd4j.rand(4, 1));
//
        sess.run(feedDict);

//
        System.out.println("MUL: " + graph.get("mul").getComputeNode().getValue());
        System.out.println("ADD: " + graph.get("add").getComputeNode().getValue());
//        System.out.println("SUB: " + graph.get("sub").getValue());
    }
}
