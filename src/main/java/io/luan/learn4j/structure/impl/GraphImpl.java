package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.visitor.Visitor;
import io.luan.learn4j.visitor.impl.DependencyVisitor;
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

    private Map<String, Integer> countMap = new HashMap<>();

    public void accept(Visitor visitor) {
        Set<Expression> sources = getSources();
        System.out.println(sources);

        for (Expression node : sources) {
            node.accept(visitor);
        }
    }

    public void add(Expression exp) {
        if (StringUtils.isBlank(exp.getName())) {
            Integer existing = countMap.get(exp.getType().name());
            int newCount = (existing != null) ? existing + 1 : 1;
            String newName = exp.getType().name() + "_" + newCount;
            countMap.put(exp.getType().name(), newCount);
            exp.setName(newName);
        }

        addInternal(exp);
        DependencyVisitor visitor = new DependencyVisitor();
        exp.accept(visitor);
        visitor.getDependencies().forEach(this::addInternal);
    }

    public Expression get(String name) {
        return nameDict.get(name);
    }
//
//    public void gradient(Expression exp, String target) {
//        ComputeNode node = exp.getComputeNode();
//        ComputeNode gradient = node.getGradient(target);
//        computeGraph.add(gradient);
//    }

    private void addInternal(Expression exp) {
        nodes.add(exp);
        String name = exp.getName();
        if (StringUtils.isNotBlank(name)) {
            nameDict.put(name, exp);
        }
    }

    private Set<Expression> getSources() {
        SourceVisitor visitor = new SourceVisitor(nodes);
        nodes.forEach(visitor::visit);

        return visitor.getSources();
    }
}
