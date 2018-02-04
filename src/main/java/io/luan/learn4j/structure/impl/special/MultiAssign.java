package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class MultiAssign extends BaseExpression {

    @Getter
    private Expression[] newValues;

    @Getter
    private Expression[] targets;

    public MultiAssign(Expression[] targets, Expression[] newValues, String name) {
        super(name);
        this.targets = targets;
        this.newValues = newValues;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitMultiAssign(this, params);
    }

    @Override
    public int[] getShape() {
        return targets[0].getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.MultiAssign;
    }

    @Override
    public boolean isInvalid() {
        boolean isInvalid = super.isInvalid();
        if (isInvalid) {
            return true;
        }
        for (Expression newVal : this.newValues) {
            if (newVal.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        for (int i = 0; i < targets.length; i++) {
            hash = hash * 31 + targets[i].getId();
            hash = hash * 31 + newValues[i].getId();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        MultiAssign other = (MultiAssign) obj;
        for (int i = 0; i < targets.length; i++) {
            if (this.getTargets()[i].getId() != other.getTargets()[i].getId()
                    || this.getNewValues()[i].getId() != other.getNewValues()[i].getId()) {
                return false;
            }
        }
        return true;
    }
}
