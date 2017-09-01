package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.MatMulNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MatMul implements Expression {

    private String name;
    private List<Expression> operands = new ArrayList<Expression>();
    private MatMulNode computeNode;

    public MatMul(String name, Expression left, Expression right) {
        this.name = name;
        operands.add(left);
        operands.add(right);

        computeNode = new MatMulNode(left.getComputeNode(), right.getComputeNode());
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
