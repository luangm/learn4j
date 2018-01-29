package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.transform.*;

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
    public void visitAdd(Add node, Object... params) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitAdd(node, params);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMatMul(node, params);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMultiply(node, params);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitDivide(node, params);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        nonSource.add(node.getBase());
        super.visitNegate(node, params);
    }

    @Override
    public void visitPower(Power node, Object... params) {
        nonSource.add(node.getBase());
        nonSource.add(node.getPower());
        super.visitPower(node, params);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        nonSource.add(node.getBase());
        super.visitReduceMean(node, params);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        nonSource.add(node.getBase());
        super.visitSigmoid(node, params);
    }

    @Override
    public void visitSoftmax(Softmax softmax, Object... params) {

    }

    @Override
    public void visitSquare(Square node, Object... params) {
        nonSource.add(node.getBase());
        super.visitSquare(node, params);
    }

    @Override
    public void visitAbsolute(Absolute node, Object... params) {
        nonSource.add(node.getBase());
        super.visitAbsolute(node, params);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitSubtract(node, params);
    }
}
