package io.luan.learn4j.expression;

import io.luan.learn4j.Tensor;
import io.luan.learn4j.compute.ComputeNode;

/**
 * A Constant expression is an Expression wrapping a Tensor value, where the value will not change.
 *
 * @author Guangmiao Luan
 * @since 24/09/2017.
 */
public class Constant extends BaseExpression {

    public static final String TYPE = "Constant";

    Constant(String name, Tensor tensor) {
        super(name);
    }

    public String getType() {
        return TYPE;
    }

    ComputeNode createComputeNode() {
        return null;
    }
}
