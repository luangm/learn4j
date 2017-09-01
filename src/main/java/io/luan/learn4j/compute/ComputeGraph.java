package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import io.luan.learn4j.compute.visitor.SourceFindingVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Interface for the compute graph which contains compute nodes
 *
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public class ComputeGraph {

    private Set<ComputeNode> nodes = new HashSet<ComputeNode>();

    private Set<ComputeNode> sourceNodes;

    public ComputeNode getRoot() {
        return nodes.iterator().next();
    }

    /**
     * Add a node to the list of all nodes
     */
    public void addNode(ComputeNode node) {
        // TODO: check to make sure a node is not already in another graph
        node.setGraph(this);
        nodes.add(node);
    }


    /**
     * Returns a subset of nodes which are not an operand to other nodes
     */
    public Set<ComputeNode> getSources() {

        if (this.sourceNodes == null) {
            SourceFindingVisitor visitor = new SourceFindingVisitor();
            visitor.setNodes(this.nodes);
            for (ComputeNode node : nodes) {
                visitor.visit(node);
            }
            this.sourceNodes = visitor.getSourceNodes();
        }

        return sourceNodes;
    }

    public Set<ComputeNode> getLeafs() {
        Set<ComputeNode> leafs = new HashSet<ComputeNode>();

        return leafs;
    }

    public void accept(ComputeVisitor visitor) {
        Set<ComputeNode> sources = getSources();

        for (ComputeNode node : sources) {
            node.accept(visitor);
        }
    }
}
