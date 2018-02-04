package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.core.Variable;
import io.luan.learn4j.structure.impl.reduction.ReduceMax;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceMin;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.*;
import io.luan.learn4j.structure.impl.transform.*;
import io.luan.learn4j.visitor.Visitor;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
abstract class BaseVisitor implements Visitor {

    @Override
    public void visitAbsolute(Absolute node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        this.preVisit(node, params);
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitAddN(AddN node, Object... params) {
        this.preVisit(node, params);
        for (Expression exp : node.getList()) {
            exp.accept(this, params);
        }
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        this.preVisit(node, params);
        node.getNewValue().accept(this, params);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        this.preVisit(node, params);
    }

    @Override
    public void visitCosine(Cosine node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        this.preVisit(node, params);
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitExponential(Exponential node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitFill(Fill node, Object... params) {
        this.preVisit(node, params);
    }

    @Override
    public void visitGroup(Group node, Object... params) {
        this.preVisit(node, params);
        for (Expression exp : node.getList()) {
            exp.accept(this, params);
        }
    }

    @Override
    public void visitLogarithm(Logarithm node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        this.preVisit(node, params);
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitMultiAssign(MultiAssign node, Object... params) {
        this.preVisit(node, params);

        for (Expression newVal : node.getNewValues()) {
            newVal.accept(this, params);
        }
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        this.preVisit(node, params);
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        this.preVisit(node, params);
    }

    @Override
    public void visitPower(Power node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
        node.getPower().accept(this, params);
    }

    @Override
    public void visitReciprocal(Reciprocal node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitReduceMax(ReduceMax node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitReduceMin(ReduceMin node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSign(Sign node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSine(Sine node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSoftmax(Softmax node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSoftmaxGrad(SoftmaxGrad node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
        node.getGrad().accept(this, params);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSquareRoot(SquareRoot node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        this.preVisit(node, params);
        node.getLeft().accept(this, params);
        node.getRight().accept(this, params);
    }

    @Override
    public void visitTangent(Tangent node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitTangentGrad(TangentGrad node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitTanh(Tanh node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitTile(Tile node, Object... params) {
        this.preVisit(node, params);
        node.getBase().accept(this, params);
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        this.preVisit(node, params);
    }

    /**
     * This is called before each visit.
     */
    protected Expression preVisit(Expression node, Object... params) {
        return null;
    }
}
