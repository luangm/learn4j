package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.special.Assign;
import io.luan.learn4j.structure.impl.transform.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

/**
 * Use this visitor to collect all the dependency of the node
 *
 * @author Guangmiao Luan
 * @since 16/09/2017.
 */
public class DependencyVisitor extends BaseVisitor {

    @Getter
    private Set<Expression> dependencies = new HashSet<Expression>();

    @Override
    protected Expression preVisit(Expression node, Object... params) {
        dependencies.add(node);
        return null;
    }
}
