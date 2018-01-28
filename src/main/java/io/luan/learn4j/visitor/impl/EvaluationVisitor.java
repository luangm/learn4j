package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.core.Variable;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.Assign;
import io.luan.learn4j.structure.impl.special.Fill;
import io.luan.learn4j.structure.impl.transform.*;
import lombok.Getter;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class EvaluationVisitor extends BaseVisitor {

    @Getter
    private Map<Expression, Tensor> feed;

    @Getter
    private Session session;

    public EvaluationVisitor(Session session, Map<Expression, Tensor> feed) {
        this.feed = feed;
        this.session = session;
    }

    @Override
    public void visitAbs(Abs node, Object... params) {
        super.visitAbs(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor abs = TensorMath.abs(base);
        session.setValue(node, abs);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        super.visitAdd(node);
        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor sum = session.getValue(node);
        if (sum == null) {
            sum = TensorMath.add(left, right);
            session.setValue(node, sum);
        } else {
            TensorMath.add(left, right, sum);
        }
    }

    @Override
    public void visitAssign(Assign node, Object... params) {
        super.visitAssign(node);
        Tensor newTensor = session.getValue(node.getNewValue());

        Expression target = node.getTarget();
        if (target instanceof Parameter) {
            Parameter targetParam = (Parameter) target;
            targetParam.setValue(newTensor);
        }

        session.setValue(node, newTensor);
        session.setValue(target, newTensor);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        session.setValue(node, node.getValue());
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        super.visitDivide(node, params);
        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor divide = session.getValue(node);
        if (divide == null) {
            divide = TensorMath.divide(left, right);
            session.setValue(node, divide);
        } else {
            divide = TensorMath.divide(left, right, divide);
        }
    }

    @Override
    public void visitFill(Fill node, Object... params) {
        super.visitFill(node, params);
        if (session.getValue(node) == null) {
            Tensor value = Tensor.fill(node.getScalar(), node.getShape());
            session.setValue(node, value);
        }
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        super.visitMatMul(node, params);
        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor prod = session.getValue(node);
        if (prod == null) {
            prod = TensorMath.matmul(left, right, node.isTransposeLeft(), node.isTransposeRight());
            session.setValue(node, prod);
        } else {
            prod = TensorMath.matmul(left, right, node.isTransposeLeft(), node.isTransposeRight(), prod);
        }
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        super.visitMultiply(node, params);
        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor prod = session.getValue(node);
        if (prod == null) {
            prod = TensorMath.multiply(left, right);
            session.setValue(node, prod);
        } else {
            prod = TensorMath.multiply(left, right, prod);
        }
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        super.visitNegate(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor negValue = TensorMath.negate(base);
        session.setValue(node, negValue);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        session.setValue(node, node.getValue());
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        super.visitReduceMean(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor reduced = TensorMath.reduceMean(base);
        session.setValue(node, reduced);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        super.visitReduceSum(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor reduced = TensorMath.reduceSum(base, node.getDimension());
        session.setValue(node, reduced);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        super.visitSigmoid(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor sigmoid = TensorMath.sigmoid(base);
        session.setValue(node, sigmoid);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object[] params) {
        super.visitSigmoidGrad(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor sigGrad = TensorMath.sigmoidGrad(base);
        session.setValue(node, sigGrad);
    }

    @Override
    public void visitSign(Sign node, Object... params) {
        super.visitSign(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor relu = TensorMath.sign(base);
        session.setValue(node, relu);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        super.visitSquare(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor squared = TensorMath.square(base);
        session.setValue(node, squared);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        super.visitStep(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor step = TensorMath.step(base);
        session.setValue(node, step);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        super.visitSubtract(node);
        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor diff = session.getValue(node);
        if (diff == null) {
            diff = TensorMath.subtract(left, right);
            session.setValue(node, diff);
        } else {
            diff = TensorMath.subtract(left, right, diff);
        }
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        Tensor feedVal = feed.get(node);
        if (feedVal != null) {
            session.setValue(node, feedVal);
        }
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        super.visitRelu(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor relu = TensorMath.relu(base);
        session.setValue(node, relu);
    }

}
