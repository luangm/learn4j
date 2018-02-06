package io.luan.learn4j.visitor.impl;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import io.luan.learn4j.session.Session;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionState;
import io.luan.learn4j.structure.impl.binary.*;
import io.luan.learn4j.structure.impl.core.Constant;
import io.luan.learn4j.structure.impl.core.Parameter;
import io.luan.learn4j.structure.impl.core.Variable;
import io.luan.learn4j.structure.impl.loss.SoftmaxCrossEntropy;
import io.luan.learn4j.structure.impl.reduction.ReduceMax;
import io.luan.learn4j.structure.impl.reduction.ReduceMean;
import io.luan.learn4j.structure.impl.reduction.ReduceMin;
import io.luan.learn4j.structure.impl.reduction.ReduceSum;
import io.luan.learn4j.structure.impl.special.*;
import io.luan.learn4j.structure.impl.transform.*;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author Guangmiao Luan
 * @since 31/08/2017.
 */
public class EvaluationVisitor extends BaseVisitor {

    private static Logger logger = LoggerFactory.getLogger(EvaluationVisitor.class);

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
        logger.info("visitCosine: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.abs(base);
        session.setValue(node, result);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitAdd(Add node, Object... params) {
        logger.info("visitAdd: " + node.getId());

        if (node.getLeft().isInvalid()) {
            node.getLeft().accept(this, params);
        }

        if (node.getRight().isInvalid()) {
            node.getRight().accept(this, params);
        }

        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor sum = session.getValue(node);
        if (sum == null) {
            sum = TensorMath.add(left, right);
            session.setValue(node, sum);
        } else {
            TensorMath.add(left, right, sum);
        }

        node.setState(ExpressionState.Evaluated);
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
        logger.info("visitAssign: " + node.getId());

        if (node.getNewValue().isInvalid()) {
            node.getNewValue().accept(this, params);
        }

        Tensor newTensor = session.getValue(node.getNewValue());

        Expression target = node.getTarget();
        if (target instanceof Parameter) {
            Parameter targetParam = (Parameter) target;
            targetParam.setValue(newTensor);
        }

        session.setValue(target, newTensor);
    }

    @Override
    public void visitConstant(Constant node, Object... params) {
        logger.info("visitConstant: " + node.getId());
        session.setValue(node, node.getValue());
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitCosine(Cosine node, Object... params) {
        logger.info("visitCosine: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.cos(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitDivide(Divide node, Object... params) {
        logger.info("visitDivide: " + node.getId());

        if (node.getLeft().isInvalid()) {
            node.getLeft().accept(this, params);
        }

        if (node.getRight().isInvalid()) {
            node.getRight().accept(this, params);
        }

        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor divide = session.getValue(node);
        if (divide == null) {
            divide = TensorMath.divide(left, right);
            session.setValue(node, divide);
        } else {
            TensorMath.divide(left, right, divide);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitExponential(Exponential node, Object... params) {
        logger.info("visitExponential: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.exp(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitFill(Fill node, Object... params) {
        logger.info("visitFill: " + node.getId());

        if (session.getValue(node) == null) {
            Tensor value = Tensor.fill(node.getScalar(), node.getShape());
            session.setValue(node, value);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitLogarithm(Logarithm node, Object... params) {
        logger.info("visitLogarithm: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.log(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitMatMul(MatMul node, Object... params) {
        logger.info("visitMatMul: " + node.getId());

        if (node.getLeft().isInvalid()) {
            node.getLeft().accept(this, params);
        }

        if (node.getRight().isInvalid()) {
            node.getRight().accept(this, params);
        }

        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor prod = session.getValue(node);
        if (prod == null) {
            prod = TensorMath.matmul(left, right, node.isTransposeLeft(), node.isTransposeRight());
            session.setValue(node, prod);
        } else {
            TensorMath.matmul(left, right, node.isTransposeLeft(), node.isTransposeRight(), prod);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitMultiAssign(MultiAssign node, Object... params) {
        logger.info("visitMultiAssign: " + node.getId());

        for (Expression newVal : node.getNewValues()) {
            if (newVal.isInvalid()) {
                newVal.accept(this, params);
            }
        }

        for (int i = 0; i < node.getTargets().length; i++) {
            Tensor newVal = session.getValue(node.getNewValues()[i]);

            Expression target = node.getTargets()[i];
            target.setValue(newVal);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitMultiply(Multiply node, Object... params) {
        logger.info("visitMultiply" + node.getId());

        if (node.getLeft().isInvalid()) {
            node.getLeft().accept(this, params);
        }

        if (node.getRight().isInvalid()) {
            node.getRight().accept(this, params);
        }

        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor prod = session.getValue(node);
        if (prod == null) {
            prod = TensorMath.multiply(left, right);
            session.setValue(node, prod);
        } else {
            TensorMath.multiply(left, right, prod);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitNegate(Negate node, Object... params) {
        logger.info("visitNegate: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor negValue = TensorMath.negate(base);
        session.setValue(node, negValue);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitParameter(Parameter node, Object... params) {
        logger.info("visitParameter: " + node.getId());
        session.setValue(node, node.getValue());
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitReciprocal(Reciprocal node, Object... params) {
        logger.info("visitReciprocal" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reciprocal(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitReduceMax(ReduceMax node, Object... params) {
        logger.info("visitReduceMax" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMax(base, node.getDimension());
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitReduceMean(ReduceMean node, Object... params) {
        logger.info("visitReduceMean" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMean(base, node.getDimension());
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitReduceMin(ReduceMin node, Object... params) {
        logger.info("visitReduceMin" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceMin(base, node.getDimension());
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitReduceSum(ReduceSum node, Object... params) {
        logger.info("visitReduceSum" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.reduceSum(base, node.getDimension());
        session.setValue(node, result);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitRelu(Relu node, Object... params) {
        logger.info("visitRelu: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.relu(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSigmoid(Sigmoid node, Object... params) {
        logger.info("visitSigmoid: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sigmoid(base);
        session.setValue(node, result);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSigmoidGrad(SigmoidGrad node, Object... params) {
        logger.info("visitSigmoidGrad: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sigmoidGrad(base);
        session.setValue(node, result);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSign(Sign node, Object... params) {
        logger.info("visitSign: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sign(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSine(Sine node, Object... params) {
        logger.info("visitSine: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sin(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSoftmax(Softmax node, Object... params) {
        logger.info("visitSoftmax: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.softmax(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSoftmaxCrossEntropy(SoftmaxCrossEntropy node, Object... params) {
        logger.info("visitSoftmaxCrossEntropy: " + node.getId());

        if (node.getLabels().isInvalid()) {
            node.getLabels().accept(this, params);
        }

        if (node.getLogits().isInvalid()) {
            node.getLogits().accept(this, params);
        }

        Tensor labels = session.getValue(node.getLabels());
        Tensor logits = session.getValue(node.getLogits());
        Tensor result = TensorMath.softmaxCrossEntropyWithLogits(labels, logits, -1);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSoftmaxGrad(SoftmaxGrad node, Object... params) {
        logger.info("visitSoftmaxGrad: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor grad = session.getValue(node.getGrad());
        Tensor result = TensorMath.softmaxGrad(base, grad);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSquare(Square node, Object... params) {
        logger.info("visitSquare" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.square(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSquareRoot(SquareRoot node, Object... params) {
        logger.info("visitSquareRoot" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.sqrt(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitStep(Step node, Object... params) {
        logger.info("visitStep" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.step(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitSubtract(Subtract node, Object... params) {
        logger.info("visitSubtract: " + node.getId());

        if (node.getLeft().isInvalid()) {
            node.getLeft().accept(this, params);
        }

        if (node.getRight().isInvalid()) {
            node.getRight().accept(this, params);
        }

        Tensor left = session.getValue(node.getLeft());
        Tensor right = session.getValue(node.getRight());

        Tensor diff = session.getValue(node);
        if (diff == null) {
            diff = TensorMath.subtract(left, right);
            session.setValue(node, diff);
        } else {
            TensorMath.subtract(left, right, diff);
        }

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitTangent(Tangent node, Object... params) {
        logger.info("visitTangent" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tan(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitTangentGrad(TangentGrad node, Object... params) {
        logger.info("visitTangentGrad" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tanGrad(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitTanh(Tanh node, Object... params) {
        logger.info("visitTanh" + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }
        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tanh(base);
        session.setValue(node, result);
        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitTile(Tile node, Object... params) {
        logger.info("visitTile: " + node.getId());

        if (node.getBase().isInvalid()) {
            node.getBase().accept(this, params);
        }

        Tensor base = session.getValue(node.getBase());
        Tensor result = TensorMath.tile(base, node.getRepeats());
        session.setValue(node, result);

        node.setState(ExpressionState.Evaluated);
    }

    @Override
    public void visitVariable(Variable node, Object... params) {
        logger.info("visitVariable: " + node.getId());
//        Tensor feedVal = feed.get(node);
//        if (feedVal != null) {
//            session.setValue(node, feedVal);
//        }
        node.setState(ExpressionState.Evaluated);
    }

}
