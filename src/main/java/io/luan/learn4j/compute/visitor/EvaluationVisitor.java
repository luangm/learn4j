package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.impl.AddNode;
import io.luan.learn4j.compute.impl.MatMulNode;
import io.luan.learn4j.compute.impl.MultiplyNode;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class EvaluationVisitor extends BaseComputeVisitor {

    private Map<String, INDArray> feed;

    public Map<String, INDArray> getFeed() {
        return this.feed;
    }

    public void setFeed(Map<String, INDArray> feed) {
        this.feed = feed;
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
    public void visitMultiply(MultiplyNode node) {
        super.visitMultiply(node);
        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();
        INDArray product = left.mul(right);
        node.setValue(product);
    }
}
