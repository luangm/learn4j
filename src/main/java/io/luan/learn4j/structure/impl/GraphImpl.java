package io.luan.learn4j.structure.impl;

import io.luan.learn4j.compute.ComputeGraph;
import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.visitor.impl.DependencyVisitor;
import io.luan.learn4j.visitor.Visitor;
import io.luan.learn4j.visitor.impl.SourceVisitor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphImpl implements Graph {

    @Getter
    private Set<Expression> nodes = new HashSet<Expression>();

    private Map<String, Expression> nameDict = new HashMap<String, Expression>();

    @Getter
    private ComputeGraph computeGraph = new ComputeGraph();

    public void add(Expression exp) {
//        addInternal(exp);
//
//        DependencyVisitor visitor = new DependencyVisitor();
//        exp.accept(visitor);
//        for (Expression dep : visitor.getDependencies()) {
//            addInternal(dep);
//        }
    }

    public Expression get(String name) {
        return nameDict.get(name);
    }

    public void accept(Visitor visitor) {
        Set<Expression> sources = getSources();
        System.out.println(sources);

        for (Expression node : sources) {
            node.accept(visitor);
        }
    }
//
//    public void gradient(Expression exp, String target) {
//        ComputeNode node = exp.getComputeNode();
//        ComputeNode gradient = node.getGradient(target);
//        computeGraph.add(gradient);
//    }

//    private void addInternal(Expression exp) {
//        nodes.add(exp);
//        String name = exp.getName();
//        if (StringUtils.isNotBlank(name)) {
//            nameDict.put(name, exp);
//        }
//
//        // Add the compute node of the exp to the compute graph.
//        computeGraph.add(exp.getComputeNode());
//    }

    private Set<Expression> getSources() {
        SourceVisitor visitor = new SourceVisitor(nodes);
        for (Expression node : nodes) {
            visitor.visit(node);
        }

        return visitor.getSources();
    }
}
