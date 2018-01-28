package io.luan.learn4j.structure.impl;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.session.impl.SessionImpl;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphImpl implements Graph {

    @Getter
    private String name;

    @Getter
    private Map<Integer, Expression> nodes = new HashMap<>();

    @Getter
    @Setter
    private Session session;

    public GraphImpl(String name) {
        this.name = name;
        this.session = new SessionImpl(this);
    }

    public Expression add(Expression exp) {
        nodes.put(exp.getId(), exp);
        exp.attach(this);
        return exp;
    }

}
