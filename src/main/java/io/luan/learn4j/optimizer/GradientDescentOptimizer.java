package io.luan.learn4j.optimizer;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 21/09/2017.
 */
public class GradientDescentOptimizer {

    @Getter
    private Graph graph;

    @Getter
    private double learnRate;

    public GradientDescentOptimizer(Graph graph, double learnRate) {
        this.graph = graph;
        this.learnRate = learnRate;
    }

    public Expression minimize(Expression loss) {
//        INDArray bGrad = loss.getComputeNode().getGradient("b").getValue();
//        bGrad.muli(this.learnRate);
//        System.out.println("bGrad" + bGrad);
//
//        INDArray wGrad = loss.getComputeNode().getGradient("W").getValue();
//        wGrad.muli(this.learnRate);
//        System.out.println("wGrad" + wGrad);
//
//        ComputeNode b = graph.get("b").getComputeNode();
//        b.getValue().subi(bGrad);
//        System.out.println("newB" + b);
//
//        ComputeNode w = graph.get("W").getComputeNode();
//        w.getValue().subi(wGrad);
//        System.out.println("newW" + w);

        return null;
    }
}
