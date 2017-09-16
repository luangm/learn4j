package io.luan.learn4j.expression;

import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.expression.visitor.ExpressionVisitor;

import java.util.Set;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Graph {

    void add(Expression exp);

    Expression get(String name);

    void accept(ExpressionVisitor visitor);

    ComputeGraph getComputeGraph();

    Set<Expression> getNodes();

}
