package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.core.utils.ShapeUtils;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.factory.ExpressionFactory;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.Fill;
import io.luan.learn4j.structure.impl.transform.*;
import lombok.Getter;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 06/10/2017.
 */
public class ReverseGradientVisitor extends BaseVisitor {

    private ExpressionFactory factory;
    private Map<Integer, List<Expression>> gradMap;
    @Getter
    private Graph graph;
    private Expression source;

    public ReverseGradientVisitor(Graph graph) {
        this.graph = graph;
        this.factory = new ExpressionFactory(graph);
    }

    /**
     * This method starts the entire visiting pattern from the source
     */
    public void visit(Expression source, Expression grad) {
        this.source = source;
        this.gradMap = new HashMap<>();
        source.accept(this, grad);
        this.finalizeInternal();
    }

    @Override
    public void visitAbsolute(Absolute node, Object... params) {
        val grad = this.preVisit(node, params);
        val sign = factory.sign(node.getBase());
        val result = factory.multiply(grad, sign);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        val grad = this.preVisit(node, params);
        val pair = ShapeUtils.getReductionIndices(node.getLeft().getShape(), node.getRight().getShape());
        val leftGrad = factory.reduceSum(grad, pair.getLeft());
        val rightGrad = factory.reduceSum(grad, pair.getRight());
        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitCosine(Cosine node, Object... params) {
        val grad = this.preVisit(node, params);
        val sine = factory.sin(node.getBase());
        val neg = factory.neg(sine);
        val result = factory.multiply(grad, neg);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        val grad = this.preVisit(node, params);
        val pair = ShapeUtils.getReductionIndices(node.getLeft().getShape(), node.getRight().getShape());

        // left = grad / right
        // right = -grad * left / right^2
        val leftDiv = factory.divide(grad, node.getRight());
        val rightDiv = factory.divide(leftDiv, node.getRight());
        val leftNeg = factory.neg(node.getLeft());
        val rightMul = factory.multiply(leftNeg, rightDiv);

        val leftGrad = factory.reduceSum(leftDiv, pair.getLeft());
        val rightGrad = factory.reduceSum(rightMul, pair.getRight());

        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitExponential(Exponential node, Object... params) {
        val grad = this.preVisit(node, params);
        val result = factory.multiply(grad, node);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitLogarithm(Logarithm node, Object... params) {
        val grad = this.preVisit(node, params);
        val result = factory.divide(grad, node.getBase());
        node.getBase().accept(this, result);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        val grad = this.preVisit(node, params);
        val leftGrad = factory.matmul(grad, node.getRight(), false, true);
        val rightGrad = factory.matmul(node.getLeft(), grad, true, false);
        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        val grad = this.preVisit(node, params);
        val pair = ShapeUtils.getReductionIndices(node.getLeft().getShape(), node.getRight().getShape());
        val leftGradMul = factory.multiply(grad, node.getRight());
        val rightGradMul = factory.multiply(node.getLeft(), grad);
        val leftGrad = factory.reduceSum(leftGradMul, pair.getLeft());
        val rightGrad = factory.reduceSum(rightGradMul, pair.getRight());
        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        val grad = this.preVisit(node, params);
        val result = factory.neg(grad);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        val grad = this.preVisit(node, params);

        int[] inputShape = node.getBase().getShape();
        int[] outputShape = node.getShape();
        int[] scale = ShapeUtils.safeDivide(inputShape, outputShape);
        val tile = factory.tile(grad, scale);

        int factor = ShapeUtils.prod(scale);
        val factorExp = factory.constant(factor);
        val result = factory.divide(tile, factorExp);

        node.getBase().accept(this, result);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        val grad = this.preVisit(node, params);
        int[] inputShape = node.getBase().getShape();
        int[] outputShape = node.getShape();
        int[] scale = ShapeUtils.safeDivide(inputShape, outputShape);
        val result = factory.tile(grad, scale);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        val grad = this.preVisit(node, params);
        val step = factory.step(node.getBase());
        val result = factory.multiply(grad, step);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        val grad = this.preVisit(node, params);
        val sigGrad = factory.sigmoidGrad(node.getBase());
        val result = factory.multiply(grad, sigGrad);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitSine(Sine node, Object... params) {
        val grad = this.preVisit(node, params);
        val cos = factory.cos(node.getBase());
        val result = factory.multiply(grad, cos);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitSoftmax(Softmax node, Object... params) {
        val grad = this.preVisit(node, params);
        val result = factory.softmaxGrad(node.getBase(), grad);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        val grad = this.preVisit(node, params);
        val mul = factory.multiply(Constant.TWO, node.getBase());
        val result = factory.multiply(grad, mul);
        node.getBase().accept(this, result);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        val grad = this.preVisit(node, params);
        val pair = ShapeUtils.getReductionIndices(node.getLeft().getShape(), node.getRight().getShape());
        val leftGrad = factory.reduceSum(grad, pair.getLeft());
        val rightGrad = factory.reduceSum(grad, pair.getRight());
        val rightGradNeg = factory.neg(rightGrad);
        node.getLeft().accept(this, leftGrad);
        node.getRight().accept(this, rightGradNeg);
    }

    @Override
    public void visitTangent(Tangent node, Object... params) {
        val grad = this.preVisit(node, params);
        val tanGrad = factory.tanGrad(node.getBase());
        val result = factory.multiply(grad, tanGrad);
        node.getBase().accept(this, result);
    }

    @Override
    protected Expression preVisit(Expression node, Object... params) {
        Expression grad = this.getGradient(node, params);
        this.addGradient(node, grad);
        return grad;
    }

    private void addGradient(Expression target, Expression grad) {
        List<Expression> list = this.gradMap.get(target.getId());
        if (list == null) {
            list = new ArrayList<>();
            this.gradMap.put(target.getId(), list);
        }
        list.add(grad);
    }

    private void finalizeInternal() {
        for (Integer key : this.gradMap.keySet()) {
            List<Expression> grads = this.gradMap.get(key);
            if (grads.size() == 1) {
                this.source.setGradient(key, grads.get(0));
            } else {
//                let addN = this.addNode(new AddN(grads));
//                this._source.setGradient(key, addN);
            }
        }
    }

    /**
     * This method returns the gradient value of the current node.
     */
    private Expression getGradient(Expression node, Object... params) {
        Expression grad = null;
        if (params.length > 0) {
            grad = (Expression) params[0];
        }

        if (grad == null) {
            grad = new Fill(1, node.getShape(), null);
        }

        return this.graph.add(grad);
    }

    private static Expression getGradientOrDefault(Expression node, Object... params) {
        if (params.length > 0) {
            return (Expression) params[0];
        }

        return new Fill(1, node.getShape(), null);
    }

}
