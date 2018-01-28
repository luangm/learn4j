package io.luan.learn4j.test.tensor;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import org.junit.Test;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class BasicTest {

    @Test
    public void testBroadcastAdd() {
        Tensor left = Tensor.ones(1, 3);
        Tensor right = Tensor.fill(5, 3, 1);
        Tensor sum = TensorMath.add(left, right);
        println(sum);
        println(left);
        println(right);
    }

    @Test
    public void testBroadcastAdd2() {
        Tensor left = Tensor.ones(1, 3);
        Tensor right = Tensor.fill(5, 3, 1);
        Tensor result = Tensor.zeros(3, 3);
        Tensor sum = TensorMath.add(left, right, result);
        println(sum);
        println(result);
        println(left);
        println(right);
    }

    @Test
    public void testCreation() {

        Tensor one = Tensor.ones(2, 3);
        println(one);

        Tensor rand = Tensor.rand(3, 2);
        println(rand);

        Tensor fill = Tensor.fill(5, new int[]{3, 2});
        println(fill);

        Tensor scalar = Tensor.scalar(3);
        println(scalar);

    }
}
