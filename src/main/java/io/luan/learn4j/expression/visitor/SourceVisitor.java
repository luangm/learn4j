package io.luan.learn4j.expression.visitor;

import io.luan.learn4j.expression.Add;
import io.luan.learn4j.expression.Expression;
import io.luan.learn4j.expression.MatMul;
import io.luan.learn4j.expression.Multiply;

import java.util.HashSet;
import java.util.Set;

/**
 * Find all the source nodes from this graph
 * <p>
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class SourceVisitor extends BaseExpressionVisitor {

    private Set<Expression> nonSource = new HashSet<Expression>();
    private Set<Expression> all;

    public SourceVisitor(Set<Expression> nodes) {
        this.all = nodes;
    }

    public Set<Expression> getSources() {
        Set<Expression> nodes = new HashSet<Expression>(all);
        nodes.removeAll(nonSource);
        return nodes;
    }

    @Override
    public void visitAdd(Add node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitAdd(node);
    }

    @Override
    public void visitMatMul(MatMul node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMatMul(node);
    }

    @Override
    public void visitMultiply(Multiply node) {
        nonSource.add(node.getLeft());
        nonSource.add(node.getRight());
        super.visitMultiply(node);
    }


}
