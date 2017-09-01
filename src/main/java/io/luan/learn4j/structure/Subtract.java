package io.luan.learn4j.structure;

import io.luan.learn4j.compute.AddNode;
import io.luan.learn4j.compute.ComputeNode;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Subtract implements Expression {

    private String name;
    private List<Expression> operands = new ArrayList<Expression>();
    private ComputeNode computeNode;

    public Subtract(String name, Expression left, Expression right) {
        this.name = name;
        operands.add(left);
        operands.add(right);
        this.computeNode = new AddNode(left.getComputeNode(), right.getComputeNode());
    }

    public String getName() {
        return name;
    }

    public Iterable<Expression> getDependencies() {
        return operands;
    }

    public ComputeNode getComputeNode() {
        return computeNode;
    }
}
