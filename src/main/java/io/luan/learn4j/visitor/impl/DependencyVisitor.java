package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.*;
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
    public void visitAdd(Add node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitAdd(node);
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        dependencies.add(node.getTarget());
        dependencies.add(node.getNewValue());
        super.visitAssign(node);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMatMul(node);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMultiply(node);
    }

    @Override
    public void visitPower(Power node, Object... params) {
        dependencies.add(node.getBase());
        dependencies.add(node.getPower());
        super.visitPower(node);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        dependencies.add(node.getBase());
        super.visitReduceMean(node);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        dependencies.add(node.getBase());
        super.visitSquare(node);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitSubtract(node);
    }
}
