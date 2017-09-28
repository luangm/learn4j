package io.luan.learn4j.structure;

import io.luan.learn4j.visitor.Visitor;

import java.util.Set;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public interface Graph {

    void accept(Visitor visitor);

    void add(Expression exp);

    Expression get(String name);

    Set<Expression> getNodes();

}
