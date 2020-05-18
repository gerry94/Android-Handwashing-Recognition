package com.example.myapplication;

public class WekaAccelClassifier {

    public static double classify(Object[] i)
            throws Exception {

        double p = Double.NaN;
        p = WekaAccelClassifier.Nafdcf6d5(i);
        return p;
    }
    static double Nafdcf6d5(Object []i) {
        double p = Double.NaN;
        if (i[16] == null) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() <= -2.2337902) {
            p = 0;
        } else if (((Double) i[16]).doubleValue() > -2.2337902) {
            p = WekaAccelClassifier.N7129a4aa6(i);
        }
        return p;
    }
    static double N7129a4aa6(Object []i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 1;
        } else if (((Double) i[11]).doubleValue() <= 0.06528709517277535) {
            p = 1;
        } else if (((Double) i[11]).doubleValue() > 0.06528709517277535) {
            p = WekaAccelClassifier.N1172ebab7(i);
        }
        return p;
    }
    static double N1172ebab7(Object []i) {
        double p = Double.NaN;
        if (i[9] == null) {
            p = 1;
        } else if (((Double) i[9]).doubleValue() <= -8.605958) {
            p = 1;
        } else if (((Double) i[9]).doubleValue() > -8.605958) {
            p = WekaAccelClassifier.N1d4c3f68(i);
        }
        return p;
    }
    static double N1d4c3f68(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 0;
        } else if (((Double) i[8]).doubleValue() <= -2.2840683) {
            p = WekaAccelClassifier.N6536f2069(i);
        } else if (((Double) i[8]).doubleValue() > -2.2840683) {
            p = 1;
        }
        return p;
    }
    static double N6536f2069(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 0;
        } else if (((Double) i[13]).doubleValue() <= 1.0) {
            p = WekaAccelClassifier.N590616fe10(i);
        } else if (((Double) i[13]).doubleValue() > 1.0) {
            p = 0;
        }
        return p;
    }
    static double N590616fe10(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 6.602777342) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 6.602777342) {
            p = 0;
        }
        return p;
    }
}
