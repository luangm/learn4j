package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import lombok.Getter;
import lombok.Setter;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
abstract class BaseNode implements ComputeNode {

    @Getter
    private String name;

    @Getter
    @Setter
    private INDArray value;

    @Getter
    @Setter
    private ComputeGraph graph;

    private Map<String, ComputeNode> gradients = new HashMap<String, ComputeNode>();

    BaseNode(String name) {
        this(name, null);
    }

    BaseNode(String name, INDArray value) {
        this.name = name;
        this.value = value;
    }

    public void accept(ComputeVisitor visitor) {
        visitor.visit(this);
    }

    public ComputeNode getGradient(String target) {
        ComputeNode gradient = gradients.get(target);
        if (gradient == null) {
            gradient = createGradientNode(target);
            gradients.put(target, gradient);
        }
        return gradient;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + this.getName() + "]";
    }

    abstract ComputeNode createGradientNode(String target);
}
