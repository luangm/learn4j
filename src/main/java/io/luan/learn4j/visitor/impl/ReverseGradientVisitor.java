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

import static io.luan.learn4j.structure.factory.ExpressionFactory.createMultiply;

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
//        Expression grad = getGradientOrDefault(node, params);
//
//        String leftGradName = node.getName() + "/grad_" + node.getLeft().getName();
//        String rightGradName = node.getName() + "/grad_" + node.getRight().getName();
//
//        val pair = ShapeUtils.getReductionIndices(node.getLeft().getShape(), node.getRight().getShape());
//
//        Expression leftGrad = createDivide(leftGradName, grad, node.getRight());
//        Expression rightGrad = createDivide(rightGradName, createNegate("", node.getLeft()), node.getRight());
//        rightGrad = createDivide("", rightGrad, node.getRight());
//        rightGrad = createMultiply("", grad, rightGrad);
//
//        leftGrad = createReduceSum(leftGradName, leftGrad, pair.getLeft());
//        rightGrad = createReduceSum(rightGradName, rightGrad, pair.getRight());
//
//        node.getLeft().accept(this, leftGrad);
//        node.getRight().accept(this, rightGrad);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        String leftGradName = node.getName() + "/grad_" + node.getLeft().getName();
        String rightGradName = node.getName() + "/grad_" + node.getRight().getName();

        Expression leftGrad = ExpressionFactory.createMatMul(leftGradName, grad, node.getRight(), false, true);
        Expression rightGrad = ExpressionFactory.createMatMul(rightGradName, node.getLeft(), grad, true, false);

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
    public void visitReduceMean(ReduceMean node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        node.getBase().accept(this, grad);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);
        node.getBase().accept(this, grad);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String gradName = node.getName() + "/grad_" + node.getBase().getName();
        String stepName = gradName + "/step";

        Expression step = ExpressionFactory.createStep(stepName, node.getBase());
        Expression result = createMultiply(gradName, grad, step);

        node.getBase().accept(this, result);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String gradName = node.getName() + "/grad_" + node.getBase().getName();
        String sigGradName = gradName + "/sigGrad";

        Expression sigGrad = ExpressionFactory.createSigmoidGrad(sigGradName, node.getBase());
        Expression result = createMultiply(gradName, grad, sigGrad);

        node.getBase().accept(this, result);
    }

    @Override
    public void visitSine(Sine node, Object... params) {
        Expression grad = this.preVisit(node, params);

        Expression cos = graph.add(new Cosine(node.getBase(), null));
        Expression result = graph.add(new Multiply(grad, cos, null));

        node.getBase().accept(this, result);
    }

    @Override
    public void visitSoftmax(Softmax softmax, Object... params) {

    }

    @Override
    public void visitSquare(Square node, Object... params) {
        Expression grad = getGradientOrDefault(node, params);

        String gradName = node.getName() + "/grad_" + node.getBase().getName();

        String mulName = gradName + "/mul";
        Expression mul = createMultiply(mulName, node.getBase(), Constant.TWO);

        Expression result = createMultiply(gradName, grad, mul);

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
