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
    public void visitAbs(Abs node, Object... params) {
        dependencies.add(node.getBase());
        super.visitAbs(node, params);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitAdd(node, params);
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        dependencies.add(node.getTarget());
        dependencies.add(node.getNewValue());
        super.visitAssign(node, params);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitDivide(node, params);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMatMul(node, params);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitMultiply(node, params);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        dependencies.add(node.getBase());
        super.visitNegate(node, params);
    }

    @Override
    public void visitPower(Power node, Object... params) {
        dependencies.add(node.getBase());
        dependencies.add(node.getPower());
        super.visitPower(node, params);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        dependencies.add(node.getBase());
        super.visitReduceMean(node, params);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        dependencies.add(node.getBase());
        super.visitSigmoid(node, params);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        dependencies.add(node.getBase());
        super.visitSquare(node, params);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        dependencies.add(node.getBase());
        super.visitStep(node, params);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        dependencies.add(node.getLeft());
        dependencies.add(node.getRight());
        super.visitSubtract(node, params);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        dependencies.add(node.getBase());
        super.visitRelu(node, params);
    }
}
