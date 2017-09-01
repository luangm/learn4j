package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.AddNode;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.MatMulNode;
import io.luan.learn4j.compute.ParameterNode;

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
public class SourceFindingVisitor extends BaseComputeVisitor {

    private Set<ComputeNode> nonSourceNodes = new HashSet<ComputeNode>();
    private Set<ComputeNode> allNodes;

    public void setNodes(Set<ComputeNode> nodes) {
        this.allNodes = nodes;
    }

    @Override
    public void visit(ComputeNode node) {
        if (nonSourceNodes.contains(node)) {
            return;
        }

        super.visit(node);
    }

    void visitAdd(AddNode node) {
        nonSourceNodes.add(node.getLeft());
        nonSourceNodes.add(node.getRight());

        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    void visitMatMul(MatMulNode node) {
        nonSourceNodes.add(node.getLeft());
        nonSourceNodes.add(node.getRight());

        node.getLeft().accept(this);
        node.getRight().accept(this);
    }

    void visitParameter(ParameterNode node) {

    }

    public Set<ComputeNode> getSourceNodes() {
        Set<ComputeNode> nodes = new HashSet<ComputeNode>(allNodes);
        nodes.removeAll(nonSourceNodes);

        return nodes;
    }
}
