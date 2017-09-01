package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
abstract class BaseNode implements ComputeNode {

    private INDArray value;

    private ComputeGraph graph;

    BaseNode() {
        this.value = null;
    }

    BaseNode(INDArray value) {
        this.value = value;
    }

    public ComputeGraph getGraph() {
        return graph;
    }

    public void setGraph(ComputeGraph graph) {
        this.graph = graph;
    }

    public void accept(ComputeVisitor visitor) {
        visitor.visit(this);
    }

    public INDArray getValue() {
        return value;
    }

    public void setValue(INDArray value) {
        this.value = value;
    }

}
