package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Interface for a compute node.
 * <p>
 * A compute node contains the actual value
 * <p>
 * A compute graph may contain multiple hidden compute nodes. Each structure graph node contains a link to a compute node
 *
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public interface ComputeNode {

    /**
     * Returns the compute graph where the node is attached.
     * <p>
     * If not attached, the call returns null
     */
    ComputeGraph getGraph();

    /**
     * Sets the graph
     */
    void setGraph(ComputeGraph graph);

    void accept(ComputeVisitor visitor);

    INDArray getValue();

    void setValue(INDArray value);

    ComputeNode getGradient(String nodeName);

    String getName();
}
