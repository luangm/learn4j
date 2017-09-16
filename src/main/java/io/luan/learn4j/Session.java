package io.luan.learn4j;

import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.visitor.EvaluationVisitor;
import io.luan.learn4j.expression.Expression;
import io.luan.learn4j.expression.Graph;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Session {

    private Graph graph;

    public Session(Graph graph) {
        this.graph = graph;
    }

    public void run(Map<String, INDArray> feedDict) {
        System.out.println("running session");
        EvaluationVisitor visitor = new EvaluationVisitor();
        visitor.setFeed(feedDict);

        ComputeGraph computeGraph = graph.getComputeGraph();
        computeGraph.accept(visitor);
    }

}
