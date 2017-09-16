package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import io.luan.learn4j.compute.visitor.DependencyVisitor;
import io.luan.learn4j.compute.visitor.SourceVisitor;

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

    /**
     * Add a node to the list of all nodes
     */
    public void add(ComputeNode node) {
        DependencyVisitor visitor = new DependencyVisitor();
        node.accept(visitor);
        for (ComputeNode dep : visitor.getDependencies()) {
            addInternal(dep);
        }

        addInternal(node);
    }

    public void accept(ComputeVisitor visitor) {
        Set<ComputeNode> sources = getSources();
        System.out.println(sources);

        for (ComputeNode node : sources) {
            node.accept(visitor);
        }
    }

    private void addInternal(ComputeNode node) {
        node.setGraph(this);
        nodes.add(node);
    }

    /**
     * Returns a subset of nodes which are not an operand to other nodes
     */
    private Set<ComputeNode> getSources() {
        SourceVisitor visitor = new SourceVisitor(nodes);
        for (ComputeNode node : nodes) {
            visitor.visit(node);
        }

        return visitor.getSources();
    }
}
