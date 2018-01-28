package io.luan.learn4j.session.impl;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.visitor.impl.EvaluationVisitor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class SessionImpl implements Session {

    @Getter
    private Graph graph;

    private Map<Integer, Tensor> valueMap = new HashMap<>();

    public SessionImpl(Graph graph) {
        this.graph = graph;
    }

    public Tensor eval(Expression exp) {
        return eval(exp, new HashMap<>());
    }

    public Tensor eval(Expression exp, Map<Expression, Tensor> feed) {
        EvaluationVisitor visitor = new EvaluationVisitor(this, feed);
        exp.accept(visitor);
        return getValue(exp);
    }

    @Override
    public Tensor getValue(Expression exp) {
        return valueMap.get(exp.getId());
    }

    @Override
    public void setValue(Expression exp, Tensor value) {
        valueMap.put(exp.getId(), value);
    }

}
