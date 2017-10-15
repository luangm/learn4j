package io.luan.learn4j.session.impl;

import io.luan.learn4j.session.FlowMode;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.Graph;
import io.luan.learn4j.structure.Tensor;
import io.luan.learn4j.visitor.impl.EvaluationVisitor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * A Session object keeps track of the Tensors from the run and subsequent runs.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class SessionImpl implements Session {

    @Getter
    private Graph graph;

    @Getter
    @Setter
    private FlowMode flowMode = FlowMode.Store;


    private Map<Expression, Tensor> valueMap = new HashMap<>();

    public SessionImpl(Graph graph) {
        this.graph = graph;
    }

    public Tensor run(Expression exp) {
        return run(exp, new HashMap<>());
    }

    public Tensor run(Expression exp, Map<Expression, Tensor> feed) {
//        System.out.println("Session.run: exp = " + exp);
//        System.out.println(valueMap);

        EvaluationVisitor visitor = new EvaluationVisitor(feed, valueMap);
        exp.accept(visitor);

        return visitor.getValue(exp);
    }


}
