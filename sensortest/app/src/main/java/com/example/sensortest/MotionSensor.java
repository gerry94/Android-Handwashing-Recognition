package com.example.sensortest;

import java.util.*;
import java.lang.Math;

import lombok.*;

@Getter
public class MotionSensor {
    private final int DIM = 50; //50Hz
    private final int NUM_FEATURES = 21;
    private List<Double> x, y, z;
    private Double[] features;

    MotionSensor() {
        x = new ArrayList<>();
        y = new ArrayList<>();
        z = new ArrayList<>();
        /*
        meanX, maxX, minX, stdX, varX, skewX, ZCRX, meanY, maxY, minY, stdY, varY, skewY, ZCRY ...
         */
        features = new Double[NUM_FEATURES];
    }

    public void add(double vx, double vy, double vz) {
        x.add(vx);
        y.add(vy);
        z.add(vz);
    }

    private double computeMean(List<Double> list) {
        double sum = 0;
        int count;

        for(count=0; count<list.size(); count++)
            sum = sum+list.get(count);

        return (sum/count);
    }

    private double computeStdDev(List<Double> list, double mean) {
        double sum = 0.0, standardDeviation = 0.0;

        for(double num: list)
            standardDeviation += Math.pow(num - mean, 2);

        return Math.sqrt(standardDeviation/list.size());
    }

    private double computeZeroCrossingRate(List<Double> list, double mean, double std) {
        double[] x = new double[DIM];
        for (int i = 0; i < DIM; i++)
            x[i] = (list.get(i) - mean)/std;

        double numCrossing = 0;
        for (int p = 0; p < DIM-1; p++)
        {
            if( ((x[p] > 0) && (x[p + 1] <= 0)) || ((x[p] < 0) && (x[p + 1] >= 0)) )
                numCrossing++;
        }

        return numCrossing;
    }

    private double computeVariance(List<Double> list, double mean) {
        double variance = 0;

        for (int i = 0; i < list.size(); i++)
            variance = variance + Math.pow(list.get(i) - mean, 2);

        return (variance / list.size());
    }

    private double computeSkewness(List<Double> list, double mean, double stdDev) {
        //[n / (n -1) (n - 2)] sum[(x_i - mean)^3] / std^3
        double v1 = list.size() / ((list.size()-1)*(list.size()-2));
        double sum = 0;

        for(int i=0; i<list.size(); i++)
            sum = sum + Math.pow(list.get(i) - mean, 3);

        return (v1*(sum/Math.pow(stdDev, 3)));
    }

    private void compute(List<Double> l, int i) {
        double mean = computeMean(l);

        features[i] = mean;
        features[i+1] = Collections.max(l);
        features[i+2] = Collections.min(l);

        double stdDev = computeStdDev(l, mean);

        features[i+3] = stdDev;
        features[i+4] = computeVariance(l, mean);
        features[i+5] = computeSkewness(l, mean, stdDev);
        features[i+6] = computeZeroCrossingRate(l, mean, stdDev);
    }
    public void computeFeatures() {
        compute(this.x, 0);
        compute(this.y, 7);
        compute(this.z, 14);
    }

    public void flush() {
        x.clear();
        y.clear();
        z.clear();
        features = new Double[NUM_FEATURES];
    }
}
