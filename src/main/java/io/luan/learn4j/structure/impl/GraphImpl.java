package io.luan.learn4j.structure.impl;

import io.luan.learn4j.session.Session;
import io.luan.learn4j.session.impl.SessionImpl;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * De-dup Algorithm: All Expressions has a hashCode method,
 * which is dependent on Expression's params. (exclude ID and state)
 * <p>
 * The Graph contains a Map (Key = hash, value = List of Expression)
 * When a key is found, then the list is compared one by one using equals method on Expression.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class GraphImpl implements Graph {

    @Getter
    private String name;

    @Getter
    private Map<Integer, Expression> nodes = new HashMap<>();

    /**
     * Key = Expression.hashCode
     */
    private Map<Integer, List<Expression>> hashCodeMap = new HashMap<>();

    @Getter
    @Setter
    private Session session;

    public GraphImpl(String name) {
        this.name = name;
        this.session = new SessionImpl(this);
    }

    public Expression add(Expression exp) {
        Expression existing = find(exp);
        if (existing != null) {
            return existing;
        }

        nodes.put(exp.getId(), exp);
        exp.attach(this);

        int hash = exp.hashCode();
        List<Expression> list = hashCodeMap.get(hash);
        if (list == null) {
            list = new ArrayList<>();
            hashCodeMap.put(hash, list);
        }
        list.add(exp);

        return exp;
    }

    @Override
    public Expression find(Expression exp) {
        List<Expression> list = hashCodeMap.get(exp.hashCode());

        if (list != null) {
            for (Expression target : list) {
                if (target.equals(exp)) {
                    return target;
                }
            }
        }
        return null;
    }
}
