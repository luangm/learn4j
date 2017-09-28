package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.structure.Expression;
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
    public void visitAdd(Add node) {
        super.visitAdd(node);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor sum = TensorMath.add(left, right);
        valueMap.put(node, sum);
    }

    @Override
    public void visitConstant(Constant node) {
        valueMap.put(node, node.getValue());
    }

    //
    @Override
    public void visitMultiply(Multiply node) {
        super.visitMultiply(node);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor prod = TensorMath.multiply(left, right);
        valueMap.put(node, prod);
    }

    @Override
    public void visitParameter(Parameter node) {
        valueMap.put(node, node.getValue());
    }
//
//    @Override
//    public void visitMatMul(MatMulNode node) {
//        super.visitMatMul(node);
//        INDArray left = node.getLeft().getValue();
//        INDArray right = node.getRight().getValue();
//        INDArray product = left.mmul(right);
//        node.setValue(product);
//    }
//

    @Override
    public void visitSubtract(Subtract node) {
        super.visitSubtract(node);
        Tensor left = valueMap.get(node.getLeft());
        Tensor right = valueMap.get(node.getRight());

        Tensor diff = TensorMath.subtract(left, right);
        valueMap.put(node, diff);
    }
//
//    @Override
//    public void visitSubtract(SubtractNode node) {
//        super.visitSubtract(node);
//        INDArray left = node.getLeft().getValue();
//        INDArray right = node.getRight().getValue();
//        INDArray sub = left.sub(right);
//        node.setValue(sub);
//    }
//
//    @Override
//    public void visitPower(PowerNode node) {
//        super.visitPower(node);
//
//        INDArray base = node.getBase().getValue();
//        INDArray power = node.getPower().getValue();
//
//        int intPower = power.getInt(0, 0);
//        double doubleBase = base.getDouble(0, 0);
//        double doubleResult = Math.pow(doubleBase, intPower);
//        INDArray result = Nd4j.zeros(1, 1).addi(doubleResult);
//        node.setValue(result);
//    }
//
//    @Override
//    public void visitReduceMean(ReduceMeanNode node) {
//        super.visitReduceMean(node);
//
//        INDArray base = node.getBase().getValue();
//        INDArray mean = base.mean(0);
//        node.setValue(mean);
//    }
//
//    @Override
//    public void visitSquare(SquareNode node) {
//        super.visitSquare(node);
//
//        INDArray base = node.getBase().getValue();
//        INDArray result = base.mul(base);
//        node.setValue(result);
//    }
}