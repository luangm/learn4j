package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.*;
import io.luan.learn4j.structure.impl.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Find all the source nodes from this graph
 * <p>
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class SourceVisitor extends BaseVisitor {

    private Set<Expression> nonSource = new HashSet<Expression>();
    private Set<Expression> all;

    public SourceVisitor(Set<Expression> nodes) {
        this.all = nodes;
    }

    public Set<Expression> getSources() {
        Set<Expression> nodes = new HashSet<Expression>(all);
        nodes.removeAll(nonSource);
        return nodes;
    }

    @Override
    public void visitAdd(Add node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitAdd(node);
    }

    @Override
    public void visitMatMul(MatMul node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMatMul(node);
    }

    @Override
    public void visitMultiply(Multiply node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMultiply(node);
    }

    @Override
    public void visitSubtract(Subtract node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitSubtract(node);
    }

    @Override
    public void visitPower(Power node) {
        nonSource.add(node.getBase());
        nonSource.add(node.getPower());
        super.visitPower(node);
    }

    @Override
    public void visitReduceMean(ReduceMean node) {
        nonSource.add(node.getBase());
        super.visitReduceMean(node);
    }

    @Override
    public void visitSquare(Square node) {
        nonSource.add(node.getBase());
        super.visitSquare(node);
    }
}
