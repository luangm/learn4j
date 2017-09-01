package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.ComputeNode;

/**
 * This is the base interface for a ComputeGraph Visitor
 *
 * New Visitors should extend BaseComputeVisitor
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public interface ComputeVisitor {

    void visit(ComputeNode node);

}
