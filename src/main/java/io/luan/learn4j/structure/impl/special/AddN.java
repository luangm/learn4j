package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * AddN node is trying to add up multiple nodes at the same time.
 * this is designed for adding up gradients, not for normal graphs.
 * This op does NOT broadcast. All nodes must have the same shape.
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class AddN extends BaseExpression {

    @Getter
    private Expression[] list;

    public AddN(Expression[] list, String name) {
        super(name);
        this.list = list;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitAddN(this, params);
    }

    @Override
    public int[] getShape() {
        return list[0].getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.AddN;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        for (Expression exp : this.list) {
            hash = hash * 31 + exp.getId();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        AddN other = (AddN) obj;
        if (this.list.length != other.list.length) {
            return false;
        }
        for (int i = 0; i < this.list.length; i++) {
            if (this.list[i].getId() != other.list[i].getId()) {
                return false;
            }
        }
        return true;
    }

}
