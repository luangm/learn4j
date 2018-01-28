package io.luan.learn4j.test.tensor;

import io.luan.learn4j.core.utils.ShapeUtils;
import org.junit.Test;

import java.util.Arrays;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class ShapeTest {

    @Test
    public void testBroadcast1() {
        int[] left = {1, 3};
        int[] right = {2, 1};
        int[] result = ShapeUtils.broadcastShapes(left, right);
        println(Arrays.toString(result));
    }

    @Test
    public void testBroadcast2() {
        int[] left = {1, 3};
        int[] right = {2, 3, 1};
        int[] result = ShapeUtils.broadcastShapes(left, right);
        println(Arrays.toString(result));
    }

    @Test
    public void testBroadcast3() {
        int[] left = {1, 1, 3, 3};
        int[] right = {2, 3, 1};
        int[] result = ShapeUtils.broadcastShapes(left, right);
        println(Arrays.toString(result));
    }

    @Test
    public void expectError() {
        int[] left = {2, 3};
        int[] right = {2, 3, 1};
        int[] result = ShapeUtils.broadcastShapes(left, right);
        println(Arrays.toString(result));
    }
}
