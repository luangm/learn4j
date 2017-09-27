package io.luan.learn4j;

import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.visitor.EvaluationVisitor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import lombok.Getter;
import lombok.Setter;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Session {

    private Graph graph;

    @Getter
    @Setter
    private FlowMode flowMode = FlowMode.Store;

    public Session(Graph graph) {
        this.graph = graph;
    }

    public Tensor run(Expression exp) {
        System.out.println("Session.run: exp = " + exp);
        EvaluationVisitor visitor = new EvaluationVisitor(new HashMap<>());
//        exp.getComputeNode().accept(visitor);
//        return Tensor.create(exp.getComputeNode().getValue());
        return Tensor.ones(1);
    }

    public Tensor run(Expression... expList) {
        System.out.println("Session.run: exp = " + expList);
        return Tensor.ones(1);
    }

    public void run(Expression exp, Map<String, Tensor> feed) {
        System.out.println("Session.run: exp = " + exp);
    }

    public void run(Map<String, INDArray> feedDict) {
        System.out.println("running session");
        EvaluationVisitor visitor = new EvaluationVisitor(feedDict);
    }

}
