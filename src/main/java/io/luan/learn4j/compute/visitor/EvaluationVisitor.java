package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.impl.*;
import lombok.Getter;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class EvaluationVisitor extends BaseComputeVisitor {

    @Getter
    private Map<String, INDArray> feed;

    public EvaluationVisitor(Map<String, INDArray> feedDict) {
        this.feed = feedDict;
    }

    @Override
    public void visitAdd(AddNode node) {
        super.visitAdd(node);
        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();
        INDArray sum = left.add(right);
        node.setValue(sum);
    }

    @Override
    public void visitMatMul(MatMulNode node) {
        super.visitMatMul(node);
        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();
        INDArray product = left.mmul(right);
        node.setValue(product);
    }

    @Override
    public void visitParameter(ParameterNode node) {
        String nodeName = node.getName();
        INDArray val = feed.get(nodeName);
        if (val != null) {
            node.setValue(val);
        }
    }

    @Override
    public void visitMultiply(MultiplyNode node) {
        super.visitMultiply(node);
        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();
        INDArray product = left.mul(right);
        node.setValue(product);
    }

    @Override
    public void visitSubtract(SubtractNode node) {
        super.visitSubtract(node);
        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();
        INDArray sub = left.sub(right);
        node.setValue(sub);
    }

    @Override
    public void visitPower(PowerNode node) {
        super.visitPower(node);

        INDArray base = node.getBase().getValue();
        INDArray power = node.getPower().getValue();

        int intPower = power.getInt(0, 0);
        double doubleBase = base.getDouble(0, 0);
        double doubleResult = Math.pow(doubleBase, intPower);
        INDArray result = Nd4j.zeros(1, 1).addi(doubleResult);
        node.setValue(result);
    }

    @Override
    public void visitReduceMean(ReduceMeanNode node) {
        super.visitReduceMean(node);

        INDArray base = node.getBase().getValue();
        INDArray mean = base.mean(0);
        node.setValue(mean);
    }

    @Override
    public void visitSquare(SquareNode node) {
        super.visitSquare(node);

        INDArray base = node.getBase().getValue();
        INDArray result = base.mul(base);
        node.setValue(result);
    }
}
