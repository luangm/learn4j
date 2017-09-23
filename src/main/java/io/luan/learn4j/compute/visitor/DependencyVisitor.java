package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.impl.*;
import io.luan.learn4j.compute.ComputeNode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Use this visitor to collect all the dependency of the node
 *
 * @author Guangmiao Luan
 * @since 16/09/2017.
 */
public class DependencyVisitor extends BaseComputeVisitor {

    @Getter
    private Set<ComputeNode> dependencies = new HashSet<ComputeNode>();

    @Override
    public void visitAdd(AddNode node) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitAdd(node);
    }

    @Override
    public void visitMatMul(MatMulNode node) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMatMul(node);
    }

    @Override
    public void visitMultiply(MultiplyNode node) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMultiply(node);
    }

    @Override
    public void visitPower(PowerNode node) {
        dependencies.add(node.getBase());
        dependencies.add(node.getPower());
        super.visitPower(node);
    }

    @Override
    public void visitSubtract(SubtractNode node) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitSubtract(node);
    }

    @Override
    public void visitReduceMean(ReduceMeanNode node) {
        dependencies.add(node.getBase());
        super.visitReduceMean(node);
    }

    @Override
    public void visitSquare(SquareNode node) {
        dependencies.add(node.getBase());
        super.visitSquare(node);
    }
}
