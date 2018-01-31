package io.luan.learn4j.test.tensor;

import io.luan.learn4j.core.Tensor;
import io.luan.learn4j.core.utils.TensorMath;
import lombok.experimental.var;
import org.junit.Test;

import java.io.IOException;

import static io.luan.learn4j.Learn4j.println;

/**
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class TestTile {

    @Test
    public void testTile() throws IOException {

        var a = Tensor.create(new double[]{1, 2, 3}, new int[]{1, 3});
        var shape = new int[]{2, 1};
        Tensor tile = TensorMath.tile(a, shape);
        println(tile);


    }

}
