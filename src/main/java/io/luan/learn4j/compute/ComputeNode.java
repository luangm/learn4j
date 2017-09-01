package io.luan.learn4j.compute;

import io.luan.learn4j.compute.visitor.ComputeVisitor;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

/**
 * Interface for a compute node.
 *
 * A compute node contains the actual value
 *
 * A compute graph may contain multiple hidden compute nodes. Each structure graph node contains a link to a compute node
 *
 * @author Guangmiao Luan
 * @since 30/08/2017.
 */
public interface ComputeNode {

    /**
     * Returns the compute graph where the node is attached.
     *
     * If not attached, the call returns null
     */
    ComputeGraph getGraph();

    /**
     * Sets the graph
     */
    void setGraph(ComputeGraph graph);

    void accept(ComputeVisitor visitor);

    List<ComputeNode> getDependencies();

    INDArray getValue();

    void setValue(INDArray value);


    ComputeNode getGradient(ParameterNode node);
}
