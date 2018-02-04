package io.luan.learn4j.structure.impl.loss;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

public class SoftmaxCrossEntropy extends BaseExpression {

    @Getter
    private Expression labels;
    @Getter
    private Expression logits;

    public SoftmaxCrossEntropy(Expression logits, Expression labels, String name) {
        super(name);
        this.logits = logits;
        this.labels = labels;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitSoftmaxCrossEntropy(this, params);
    }

    @Override
    public int[] getShape() {
        return logits.getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.SoftmaxCrossEntropy;
    }

    @Override
    public boolean isInvalid() {
        return super.isInvalid() || logits.isInvalid() || labels.isInvalid();
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + logits.getId();
        hash = hash * 31 + labels.getId();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        SoftmaxCrossEntropy other = (SoftmaxCrossEntropy) obj;
        return this.getLogits().getId() == other.getLogits().getId()
                && this.getLabels().getId() == other.getLabels().getId();
    }
}
