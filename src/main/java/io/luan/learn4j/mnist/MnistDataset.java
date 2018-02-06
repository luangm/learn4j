package io.luan.learn4j.mnist;

import io.luan.learn4j.core.Tensor;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.nd4j.linalg.factory.Nd4j;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @author Guangmiao Luan
 * @since 06/02/2018.
 */
public class MnistDataset {

    private static String TRAIN_IMAGE_FILE = "/tmp/mnist/train-images-idx3-ubyte.gz";
    private static String TRAIN_LABEL_FILE = "/tmp/mnist/train-labels-idx1-ubyte.gz";
    private static String TEST_IMAGE_FILE = "/tmp/mnist/t10k-images-idx3-ubyte.gz";
    private static String TEST_LABEL_FILE = "/tmp/mnist/t10k-labels-idx1-ubyte.gz";

    private static float[] TRAIN_IMAGE_DATA;
    private static int[] TRAIN_LABEL_DATA;
    private static float[] TEST_IMAGE_DATA;
    private static int[] TEST_LABEL_DATA;

    static {
        init1();
        initTrainLabels();
    }

    public Tensor getData(int count) {
        float[] floats = new float[28 * 28 * count];
        for (int i = 0; i < floats.length; i++) {
            floats[i] = TRAIN_IMAGE_DATA[i];
        }
        return Tensor.create(Nd4j.create(floats, new int[]{count, 28, 28}));
    }

    public Tensor getLabels(int count) {
        float[] floats = new float[10 * count];
        for (int i = 0; i < count; i++) {
            int label = TRAIN_LABEL_DATA[i];

            for (int j = 0; j < 10; j++) {
                floats[i * 10 + j] = j == label ? 1 : 0;
            }

        }
        return Tensor.create(Nd4j.create(floats, new int[]{count, 10}));
    }

    private static void download1() {
        File file = new File(TRAIN_IMAGE_FILE);
        if (file.exists()) {
            return;
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://yann.lecun.com/exdb/mnist/t10k-images-idx3-ubyte.gz");

        try {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();

                BufferedInputStream bis = new BufferedInputStream(entity.getContent());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(TRAIN_IMAGE_FILE)));
                int inByte;
                while ((inByte = bis.read()) != -1) bos.write(inByte);
                bis.close();
                bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void init1() {
        try {
            GZIPInputStream input = new GZIPInputStream(new FileInputStream(TRAIN_IMAGE_FILE));

            long skip = input.skip(16);
            byte[] bytes = IOUtils.toByteArray(input);
            System.out.println(bytes.length);
            TRAIN_IMAGE_DATA = new float[100 * 784];
            for (int i = 0; i < TRAIN_IMAGE_DATA.length; i++) {
                TRAIN_IMAGE_DATA[i] = (float) Byte.toUnsignedInt(bytes[i]) * (1 / 255.0f);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initTrainLabels() {
        try {
            GZIPInputStream input = new GZIPInputStream(new FileInputStream(TRAIN_LABEL_FILE));

            long skip = input.skip(8);
            byte[] bytes = IOUtils.toByteArray(input);
            System.out.println(bytes.length);
            TRAIN_LABEL_DATA = new int[100];
            for (int i = 0; i < TRAIN_LABEL_DATA.length; i++) {
                TRAIN_LABEL_DATA[i] = Byte.toUnsignedInt(bytes[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void initTestLabels() {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://yann.lecun.com/exdb/mnist/t10k-labels-idx1-ubyte.gz");
//
//        try {
//            try (CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
//                System.out.println(response1.getStatusLine());
//                HttpEntity entity1 = response1.getEntity();
//
//                GZIPInputStream input = new GZIPInputStream(entity1.getContent());
//                long skip = input.skip(8);
//                byte[] bytes = IOUtils.toByteArray(input);
//                System.out.println(bytes.length);
//                labels = new int[bytes.length];
//                for (int i = 0; i < bytes.length; i++) {
//                    labels[i] = Byte.toUnsignedInt(bytes[i]);
//                }
//
//                EntityUtils.consume(entity1);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
