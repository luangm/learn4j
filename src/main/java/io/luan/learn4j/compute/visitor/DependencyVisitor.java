package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.AddNode;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.MatMulNode;
import io.luan.learn4j.compute.MultiplyNode;
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
}
