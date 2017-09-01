package io.luan.learn4j.structure;

import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.visitor.ComputeVisitor;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Graph {

    public Expression node;
    public Map<String, Expression> nodeDict = new HashMap<String, Expression>();
    public ComputeGraph computeGraph = new ComputeGraph();

    public void add(Expression exp) {
        node = exp;
        addInternal(exp);
    }

    public Expression get(String name) {
        return nodeDict.get(name);
    }

    public void accept(ComputeVisitor visitor) {
        this.computeGraph.accept(visitor);
    }

    private void addInternal(Expression exp) {
        String name = exp.getName();
        if (StringUtils.isNotBlank(name)) {
            nodeDict.put(name, exp);
        }

        // Add the compute node of the exp to the compute graph.
        computeGraph.addNode(exp.getComputeNode());

        for (Expression dep : exp.getDependencies()) {
            addInternal(dep);
        }
    }
}
