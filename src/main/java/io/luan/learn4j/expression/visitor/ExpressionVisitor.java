package io.luan.learn4j.expression.visitor;

import io.luan.learn4j.expression.*;

/**
 * This is the base interface for a Structure Graph Visitor
 * <p>
 * New Visitors should extend BaseExpressionVisitor
 *
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public interface ExpressionVisitor {

    void visit(Expression node);

    void visitAdd(Add node);

    void visitParameter(Parameter node);

    void visitMatMul(MatMul node);

    void visitMultiply(Multiply node);

    void visitPower(Power node);

    void visitSubtract(Subtract node);

    void visitReduceMean(ReduceMean node);

    void visitSquare(Square node);
}
