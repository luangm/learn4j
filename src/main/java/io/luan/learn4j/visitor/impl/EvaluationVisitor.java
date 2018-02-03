package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.core.Variable;
import io.luan.learn4j.structure.impl.reduction.ReduceMax;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceMin;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.AddN;
import io.luan.learn4j.structure.impl.special.Assign;
import io.luan.learn4j.structure.impl.special.Fill;
import io.luan.learn4j.structure.impl.special.Tile;
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
    public void visitAbsolute(Absolute node, Object... params) {
        super.visitAbsolute(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.abs(base);
        session.setValue(node, result);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        super.visitAdd(node, params);
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
    public void visitAddN(AddN node, Object... params) {
        super.visitAddN(node, params);

        Tensor result = Tensor.zeros(node.getShape());
        for (int i = 0; i < node.getList().length; i++) {
            Tensor value = session.getValue(node.getList()[i]);
            TensorMath.add(result, value, result);
        }
        session.setValue(node, result);
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
    public void visitCosine(Cosine node, Object... params) {
        super.visitCosine(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.cos(base);
        session.setValue(node, result);
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
            TensorMath.divide(left, right, divide);
        }
    }

    @Override
    public void visitExponential(Exponential node, Object... params) {
        super.visitExponential(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.exp(base);
        session.setValue(node, result);
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
    public void visitLogarithm(Logarithm node, Object... params) {
        super.visitLogarithm(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.log(base);
        session.setValue(node, result);
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
    public void visitReciprocal(Reciprocal node, Object... params) {
        super.visitReciprocal(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reciprocal(base);
        session.setValue(node, result);
    }

    @Override
    public void visitReduceMax(ReduceMax node, Object... params) {
        super.visitReduceMax(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMax(base, node.getDimension());
        session.setValue(node, result);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        super.visitReduceMean(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMean(base, node.getDimension());
        session.setValue(node, result);
    }

    @Override
    public void visitReduceMin(ReduceMin node, Object... params) {
        super.visitReduceMin(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMin(base, node.getDimension());
        session.setValue(node, result);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        super.visitReduceSum(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceSum(base, node.getDimension());
        session.setValue(node, result);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        super.visitRelu(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.relu(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        super.visitSigmoid(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sigmoid(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object... params) {
        super.visitSigmoidGrad(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sigmoidGrad(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSign(Sign node, Object... params) {
        super.visitSign(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sign(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSine(Sine node, Object... params) {
        super.visitSine(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sin(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSoftmax(Softmax node, Object... params) {
        super.visitSoftmax(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.softmax(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSoftmaxGrad(SoftmaxGrad node, Object... params) {
        super.visitSoftmaxGrad(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor grad = session.getValue(node.getGrad());
        Tensor result = TensorMath.softmaxGrad(base, grad);
        session.setValue(node, result);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        super.visitSquare(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.square(base);
        session.setValue(node, result);
    }

    @Override
    public void visitSquareRoot(SquareRoot node, Object... params) {
        super.visitSquareRoot(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sqrt(base);
        session.setValue(node, result);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        super.visitStep(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.step(base);
        session.setValue(node, result);
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
    public void visitTangent(Tangent node, Object... params) {
        super.visitTangent(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tan(base);
        session.setValue(node, result);
    }

    @Override
    public void visitTangentGrad(TangentGrad node, Object... params) {
        super.visitTangentGrad(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tanGrad(base);
        session.setValue(node, result);
    }

    @Override
    public void visitTanh(Tanh node, Object... params) {
        super.visitTanh(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tanh(base);
        session.setValue(node, result);
    }

    @Override
    public void visitTile(Tile node, Object... params) {
        super.visitTile(node, params);
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tile(base, node.getRepeats());
        session.setValue(node, result);
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        Tensor feedVal = feed.get(node);
        if (feedVal != null) {
            session.setValue(node, feedVal);
        }
    }

}
