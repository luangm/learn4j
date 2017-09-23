package io.luan.learn4j.test.pure;

import org.junit.Test;

import java.util.Random;

/**
 * This test is to recreate a linear-regression model with only Java
 * <p>
 * Y = m * X + b
 *
 * @author Guangmiao Luan
 * @since 28/08/2017.
 */
public class PureTest {

    public static final double M = 2.2;
    public static final double B = -0.3;

    public static Data genData(int count) {
        Random rand = new Random();
        Data data = new Data();
        data.X = new double[count];
        data.Y = new double[count];

        for (int i = 0;  i < count; i++) {


            double x = rand.nextDouble();
            double y = M * x + B + (rand.nextDouble() - 0.5);

            data.X[i] = x;
            data.Y[i] = y;

            System.out.println("x = " + x + " \t y = " + y);
        }

        return data;
    }

    @Test
    public void linearRegressionGradientDescent() {

        double m = 0;
        double b = 0;
        double learnRate = 0.05;

        Data data = genData(10000);
        double[] X = data.X;
        double[] Y = data.Y;

        System.out.println("m:" + m);
        System.out.println("b:" + b);

        int epoch = 0;
        while (epoch < 10000) {

            epoch++;

            if (epoch % 10 == 0) {
                System.out.println("Epoch: " + epoch + "\t\t m = " + m + "\t\t b = " + b);
            }

            double m_grad = 0;
            double b_grad = 0;

            for (int i = 0; i < X.length; i++) {
                double x = X[i];
                double y = Y[i];

                double guess = m * x + b;
                double error = guess - y;

                m_grad += (error * x) / X.length;
                b_grad += error / X.length;
            }

            m -= m_grad * learnRate;
            b -= b_grad * learnRate;
        }

    }
}

class Data {
    double[] X;
    double[] Y;
}
