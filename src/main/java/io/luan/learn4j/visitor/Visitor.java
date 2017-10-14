package io.luan.learn4j.visitor;

import io.luan.learn4j.structure.Expression;

/**
 * This is the base interface for a Graph Visitor
 * <p>
 * New Visitors should extend BaseVisitor
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public interface Visitor {

    void visit(Expression node, Object... params);
}
