package io.luan.learn4j.core.utils;

import org.apache.commons.lang3.tuple.Pair;
import org.nd4j.linalg.convolution.Convolution;

/**
 * @author Guangmiao Luan
 * @since 19/10/2017.
 */
public class ConvUtils {

    public static int outSize(int size, int kernel, int stride, int padding) {
        return Convolution.outSize(size, kernel, stride, padding, false);
    }
}
