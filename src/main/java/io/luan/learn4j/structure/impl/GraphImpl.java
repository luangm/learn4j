package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphImpl implements Graph {

    @Getter
    private Map<Integer, Expression> nodes = new HashMap<>();

    public Expression add(Expression exp) {
        nodes.put(exp.getId(), exp);
        return exp;
    }

}
