package io.luan.learn4j.compute.visitor;

import io.luan.learn4j.compute.AddNode;
import io.luan.learn4j.compute.MatMulNode;
import io.luan.learn4j.compute.ParameterNode;
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
    void visitAdd(AddNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);

        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();

        INDArray sum = left.add(right);

        node.setValue(sum);
    }

    void visitParameter(ParameterNode node) {
        // do nothing
    }


    void visitMatMul(MatMulNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);

        INDArray left = node.getLeft().getValue();
        INDArray right = node.getRight().getValue();

        INDArray product = left.mmul(right);

        node.setValue(product);
    }

}
