package io.luan.learn4j.structure.impl.special;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.structure.Expression;
import io.luan.learn4j.structure.ExpressionType;
import io.luan.learn4j.structure.impl.base.BaseExpression;
import io.luan.learn4j.visitor.Visitor;
import lombok.Getter;

/**
 * A Group Expression groups multiple Expression together
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Group extends BaseExpression {

    @Getter
    private Expression[] list;

    public Group(Expression[] list, String name) {
        super(name);
        this.list = list;
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitGroup(this, params);
    }

    @Override
    public int[] getShape() {
        return list[0].getShape();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Group;
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
        Group other = (Group) obj;
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

    @Override
    public String toString() {
        int hash = this.hashCode();
        String result = this.getType() + "[" + this.getId() + "][" + hash + "]: \n";

        for (Expression item : this.list) {
            result += item.toString() + "\n";
        }
        return result;
    }
}
