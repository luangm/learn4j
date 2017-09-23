package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Find all the source nodes from this graph
 * <p>
 * This algorithm starts with
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class SourceVisitor extends BaseComputeVisitor {

    private Set<ComputeNode> nonSource = new HashSet<ComputeNode>();
    private Set<ComputeNode> all;

    public SourceVisitor(Set<ComputeNode> nodes) {
        this.all = nodes;
    }

    public Set<ComputeNode> getSources() {
        Set<ComputeNode> nodes = new HashSet<ComputeNode>(all);
        nodes.removeAll(nonSource);
        return nodes;
    }

    @Override
    public void visitAdd(AddNode node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitAdd(node);
    }

    @Override
    public void visitMatMul(MatMulNode node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMatMul(node);
    }

    @Override
    public void visitMultiply(MultiplyNode node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMultiply(node);
    }

    @Override
    public void visitSubtract(SubtractNode node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitSubtract(node);
    }

    @Override
    public void visitPower(PowerNode node) {
        nonSource.add(node.getBase());
        nonSource.add(node.getPower());
        super.visitPower(node);
    }

    @Override
    public void visitReduceMean(ReduceMeanNode node) {
        nonSource.add(node.getBase());
        super.visitReduceMean(node);
    }

    @Override
    public void visitSquare(SquareNode node) {
        nonSource.add(node.getBase());
        super.visitSquare(node);
    }
}
