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
public class Tile extends BaseExpression {

    @Getter
    private Expression base;
    @Getter
    private int[] repeats;
    @Getter
    private int[] shape;

    public Tile(Expression base, int[] repeats, String name) {
        super(name);
        this.base = base;
        this.repeats = repeats;
        this.shape = new int[0];
    }

    @Override
    public void accept(Visitor visitor, Object... params) {
        visitor.visitTile(this, params);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.Tile;
    }

    @Override
    public boolean isInvalid() {
        return super.isInvalid() || base.isInvalid();
    }
}
