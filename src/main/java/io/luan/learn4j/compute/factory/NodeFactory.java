package io.luan.learn4j.compute.factory;

import io.luan.learn4j.compute.ComputeNode;
import io.luan.learn4j.compute.impl.*;

/**
 * The NodeFactory class implements the Factory pattern for object creation.
 * <p>
 * The reason for a separate Factory class instead of constructors is simplification.
 * <p>
 * Whenever a node is created, it is simplified and the simplified node is returned.
 *
 * @author Guangmiao Luan
 * @since 17/09/2017.
 */
public class NodeFactory {

    public static ComputeNode createAddNode(String name, ComputeNode left, ComputeNode right) {

        if (left == ConstantNode.ZERO) {
            return right;
        }

        if (right == ConstantNode.ZERO) {
            return left;
        }

        return new AddNode(name, left, right);
    }

    public static ComputeNode createMultiplyNode(String name, ComputeNode left, ComputeNode right) {

        if (left == ConstantNode.IDENTITY) {
            return right;
        }
        if (left == ConstantNode.ZERO) {
            return ConstantNode.ZERO;
        }
        if (right == ConstantNode.IDENTITY) {
            return left;
        }
        if (right == ConstantNode.ZERO) {
            return ConstantNode.ZERO;
        }

        return new MultiplyNode(name, left, right);
    }

    public static ComputeNode createPowerNode(String name, ComputeNode base, ComputeNode power) {

        return new PowerNode(name, base, power);
    }

    public static ComputeNode createSubtractNode(String name, ComputeNode left, ComputeNode right) {

        return new SubtractNode(name, left, right);
    }

    public static ComputeNode createReduceMeanNode(String name, ComputeNode base, int dimension) {
        return new ReduceMeanNode(name, base, dimension);
    }
}
