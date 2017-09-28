package io.luan.learn4j.structure.impl;

import io.luan.learn4j.structure.Expression;
import lombok.Getter;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class Sigmoid {

    @Getter
    private Expression exp;

    public Sigmoid(String name, Expression exp) {
        this.exp = exp;
    }

    public Iterable<Expression> getDependencies() {
        return null;
    }

}
