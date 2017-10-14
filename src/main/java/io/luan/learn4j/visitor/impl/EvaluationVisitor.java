package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.impl.*;
import io.luan.learn4j.utils.TensorMath;
import lombok.Getter;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class EvaluationVisitor extends BaseVisitor {

    @Getter
    private Map<Expression, Tensor> feedMap;

    @Getter
    private Map<Expression, Tensor> valueMap;

    public EvaluationVisitor(Map<Expression, Tensor> feedMap, Map<Expression, Tensor> valueMap) {
        this.feedMap = feedMap;
        this.valueMap = valueMap;
    }

    public Tensor getValue(Expression exp) {
        return valueMap.get(exp);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        super.visitAdd(node);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor sum = TensorMath.add(left, right);
        valueMap.put(node, sum);
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        super.visitAssign(node);
        Tensor newTensor = valueMap.get(node.getNewValue());

        Expression target = node.getTarget();
        if (target instanceof Parameter) {
            Parameter targetParam = (Parameter) target;
            targetParam.setValue(newTensor);
        }

        valueMap.put(node, newTensor);
        valueMap.put(target, newTensor);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        valueMap.put(node, node.getValue());
    }

    @Override
    public void visitFill(Fill node, Object... params) {
        super.visitFill(node, params);
        Tensor value = Tensor.fill(node.getScalar(), node.getShape());
        valueMap.put(node, value);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        super.visitMatMul(node, params);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor prod = TensorMath.matmul(left, right, node.isTransposeLeft(), node.isTransposeRight());
        valueMap.put(node, prod);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        super.visitMultiply(node, params);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor prod = TensorMath.multiply(left, right);
        valueMap.put(node, prod);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        super.visitNegate(node, params);
        Tensor base = valueMap.get(node.getBase());
        Tensor negValue = TensorMath.negate(base);
        valueMap.put(node, negValue);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        valueMap.put(node, node.getValue());
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        super.visitReduceMean(node, params);
        Tensor base = valueMap.get(node.getBase());
        Tensor reduced = TensorMath.reduceMean(base);
        valueMap.put(node, reduced);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        super.visitSigmoid(node, params);
        Tensor base = valueMap.get(node.getBase());
        Tensor sigmoid = TensorMath.sigmoid(base);
        valueMap.put(node, sigmoid);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object[] params) {
        super.visitSigmoidGrad(node, params);
        Tensor base = valueMap.get(node.getBase());
        Tensor sigGrad = TensorMath.sigmoidGrad(base);
        valueMap.put(node, sigGrad);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        super.visitSquare(node, params);
        Tensor base = valueMap.get(node.getBase());
        Tensor squared = TensorMath.square(base);
        valueMap.put(node, squared);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        super.visitSubtract(node);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor diff = TensorMath.subtract(left, right);
        valueMap.put(node, diff);
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        Tensor feedVal = feedMap.get(node);
        if (feedVal != null) {
            valueMap.put(node, feedVal);
        }
    }

}
