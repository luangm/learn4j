package io.luan.learn4j.test.basics;

import io.luan.learn4j.*;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.ParameterNode;
import io.luan.learn4j.compute.visitor.EvaluationVisitor;
import io.luan.learn4j.structure.*;
import org.junit.Test;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Set;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class BasicTest {

    @Test
    public void testAnd() {

        Parameter b = Learn4j.parameter("b", Nd4j.ones(4, 1));
        Parameter c = Learn4j.parameter("c", Nd4j.ones(4, 1));

        Parameter W = Learn4j.parameter("W", Nd4j.rand(4, 3));
        Parameter x = Learn4j.parameter("x" , Nd4j.ones(3, 1));

//        Expression h = Learn4j.add("add", Learn4j.mmul("mul", W, x), b);
//        Variable y = Learn4j.variable("y", new int[] {4, 1});
//        Expression loss = Learn4j.subtract("sub", h, y);

        Add add = Learn4j.add("add", b, c);
        MatMul mmul = Learn4j.mmul("mmul", W, x);

        Graph graph = new Graph();
        graph.add(b);
        graph.add(c);
        graph.add(add);
        graph.add(W);
        graph.add(x);
        graph.add(mmul);

        System.out.println(graph.nodeDict);
        System.out.println(graph.computeGraph);
        System.out.println(graph);

        Set<ComputeNode> sources = graph.computeGraph.getSources();
        System.out.println(sources);

        EvaluationVisitor visitor = new EvaluationVisitor();
        graph.accept(visitor);

        System.out.println(graph);

//        Session sess = new Session(graph);
//
//        Map<String, INDArray> feedDict = new HashMap<String, INDArray>();
//        feedDict.put("x", Nd4j.rand(3, 1));
//        feedDict.put("y", Nd4j.rand(4, 1));
//
//        sess.run(feedDict);

//
//        System.out.println("MUL: " + graph.get("mul").getValue());
//        System.out.println("ADD: " + graph.get("add").getValue());
//        System.out.println("SUB: " + graph.get("sub").getValue());
    }
}
