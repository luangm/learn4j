package io.luan.learn4j.utils;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Guangmiao Luan
 * @since 19/10/2017.
 */
public class ShapeUtils {

    public static int[] reduce(int[] shape, int dimension) {
        int[] result = new int[shape.length];
        for (int i = 0; i < shape.length; i++) {
            result[i] = (dimension == -1 || dimension == i) ? 1 : shape[i];
        }
        return result;
    }

    public static int[] broadcastShapes(int[] shape1, int[] shape2) {

        int[] result = new int[2];
        for (int i = 0; i < result.length; i++) {
            int left = shape1[i];
            int right = shape2[i];

            if (left != 1 && right != 1 && left != right) {
                throw new IllegalStateException("cannot broadcast shapes");
            }

            result[i] = Math.max(left, right);
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

    public static boolean isSameShape(int[] left, int[] right) {
        for (int i = 0; i < left.length; i++) {
            if (left[i] != right[i]) {
                return false;
            }
        }
        return true;
    }
}
