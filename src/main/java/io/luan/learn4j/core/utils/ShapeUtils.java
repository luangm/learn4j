package io.luan.learn4j.core.utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Guangmiao Luan
 * @since 19/10/2017.
 */
public class ShapeUtils {

    public static int[] broadcastShapes(int[] leftShape, int[] rightShape) {

        int[] result = leftShape.length >= rightShape.length ? leftShape.clone() : rightShape.clone();
        int rank = result.length;
        int leftIndex = leftShape.length - 1;
        int rightIndex = rightShape.length - 1;

        for (int i = 0; i < rank; i++) {
            if (leftIndex < 0 || rightIndex < 0) {
                break;
            }

            int left = leftShape[leftIndex];
            int right = rightShape[rightIndex];

            if (left != 1 && right != 1 && left != right) {
                throw new IllegalArgumentException("Incompatible shapes, cannot broadcast");
            }

            if (rightIndex > leftIndex) {
                result[rightIndex] = Math.max(left, right);
            } else {
                result[leftIndex] = Math.max(left, right);
            }

            leftIndex--;
            rightIndex--;
        }

        return result;
    }

    public static int prod(int[] shape) {
        int result = 1;
        for (int dim : shape) {
            result *= dim;
        }
        return result;
    }

    /**
     * By calling this method the caller ensures the two shapes have the same rank
     * and can be cleanly divided.
     * Normally used on reduction.
     */
    public static int[] safeDivide(int[] shape1, int[] shape2) {
        int[] result = new int[shape1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = shape1[i] / shape2[i];
        }
        return result;
    }

    public static Pair<Integer, Integer> getReductionIndices(int[] shape1, int[] shape2) {

        int[] resultShape = broadcastShapes(shape1, shape2);
        int left = -1;
        int right = -1;
        for (int i = 0; i < shape1.length; i++) {
            if (shape1[i] == 1 && shape1[i] != resultShape[i]) {
                left = i;
            }

            if (shape2[i] == 1 && shape2[i] != resultShape[i]) {
                right = i;
            }
        }

        return Pair.of(left, right);
    }

    public static int[] reduce(int[] shape, int dimension) {
        int[] result = new int[shape.length];
        for (int i = 0; i < shape.length; i++) {
            result[i] = (dimension == -1 || dimension == i) ? 1 : shape[i];
        }
        return result;
    }
}
