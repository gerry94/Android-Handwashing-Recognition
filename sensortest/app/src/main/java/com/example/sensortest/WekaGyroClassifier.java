package com.example.sensortest;

public class WekaGyroClassifier {

    public static double classify(Object[] i)
            throws Exception {

        double p = Double.NaN;
        p = WekaGyroClassifier.N27071a0432(i);
        return p;
    }
    static double N27071a0432(Object []i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 1;
        } else if (((Double) i[11]).doubleValue() <= 0.013787374362498972) {
            p = 1;
        } else if (((Double) i[11]).doubleValue() > 0.013787374362498972) {
            p = WekaGyroClassifier.N234b270633(i);
        }
        return p;
    }
    static double N234b270633(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 0;
        } else if (((Double) i[1]).doubleValue() <= 1.856305) {
            p = WekaGyroClassifier.N65ccd60334(i);
        } else if (((Double) i[1]).doubleValue() > 1.856305) {
            p = 0;
        }
        return p;
    }
    static double N65ccd60334(Object []i) {
        double p = Double.NaN;
        if (i[11] == null) {
            p = 1;
        } else if (((Double) i[11]).doubleValue() <= 0.5406894941917607) {
            p = WekaGyroClassifier.N5261091735(i);
        } else if (((Double) i[11]).doubleValue() > 0.5406894941917607) {
            p = WekaGyroClassifier.N752a3b2c46(i);
        }
        return p;
    }
    static double N5261091735(Object []i) {
        double p = Double.NaN;
        if (i[17] == null) {
            p = 0;
        } else if (((Double) i[17]).doubleValue() <= 0.2524097920950087) {
            p = WekaGyroClassifier.N4da9fe6536(i);
        } else if (((Double) i[17]).doubleValue() > 0.2524097920950087) {
            p = WekaGyroClassifier.N54c16f3d44(i);
        }
        return p;
    }
    static double N4da9fe6536(Object []i) {
        double p = Double.NaN;
        if (i[18] == null) {
            p = 1;
        } else if (((Double) i[18]).doubleValue() <= 0.005624024901747546) {
            p = 1;
        } else if (((Double) i[18]).doubleValue() > 0.005624024901747546) {
            p = WekaGyroClassifier.N37a083c137(i);
        }
        return p;
    }
    static double N37a083c137(Object []i) {
        double p = Double.NaN;
        if (i[8] == null) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() <= 0.11515935) {
            p = 1;
        } else if (((Double) i[8]).doubleValue() > 0.11515935) {
            p = WekaGyroClassifier.N345f27f838(i);
        }
        return p;
    }
    static double N345f27f838(Object []i) {
        double p = Double.NaN;
        if (i[1] == null) {
            p = 1;
        } else if (((Double) i[1]).doubleValue() <= 0.11411645) {
            p = WekaGyroClassifier.N6eacb9d539(i);
        } else if (((Double) i[1]).doubleValue() > 0.11411645) {
            p = WekaGyroClassifier.N6c7428b340(i);
        }
        return p;
    }
    static double N6eacb9d539(Object []i) {
        double p = Double.NaN;
        if (i[7] == null) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() <= -0.1117062899326) {
            p = 0;
        } else if (((Double) i[7]).doubleValue() > -0.1117062899326) {
            p = 1;
        }
        return p;
    }
    static double N6c7428b340(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 0;
        } else if (((Double) i[13]).doubleValue() <= 4.0) {
            p = WekaGyroClassifier.N5f79107741(i);
        } else if (((Double) i[13]).doubleValue() > 4.0) {
            p = WekaGyroClassifier.N12d48c8742(i);
        }
        return p;
    }
    static double N5f79107741(Object []i) {
        double p = Double.NaN;
        if (i[19] == null) {
            p = 0;
        } else if (((Double) i[19]).doubleValue() <= -0.3740745921722094) {
            p = 0;
        } else if (((Double) i[19]).doubleValue() > -0.3740745921722094) {
            p = 1;
        }
        return p;
    }
    static double N12d48c8742(Object []i) {
        double p = Double.NaN;
        if (i[12] == null) {
            p = 0;
        } else if (((Double) i[12]).doubleValue() <= 0.3628101301241974) {
            p = 0;
        } else if (((Double) i[12]).doubleValue() > 0.3628101301241974) {
            p = WekaGyroClassifier.N193dfd1943(i);
        }
        return p;
    }
    static double N193dfd1943(Object []i) {
        double p = Double.NaN;
        if (i[0] == null) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() <= 0.16532770795600002) {
            p = 1;
        } else if (((Double) i[0]).doubleValue() > 0.16532770795600002) {
            p = 0;
        }
        return p;
    }
    static double N54c16f3d44(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() <= 9.0) {
            p = WekaGyroClassifier.N432c377a45(i);
        } else if (((Double) i[13]).doubleValue() > 9.0) {
            p = 0;
        }
        return p;
    }
    static double N432c377a45(Object []i) {
        double p = Double.NaN;
        if (i[10] == null) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() <= 0.15326952347693465) {
            p = 0;
        } else if (((Double) i[10]).doubleValue() > 0.15326952347693465) {
            p = 1;
        }
        return p;
    }
    static double N752a3b2c46(Object []i) {
        double p = Double.NaN;
        if (i[20] == null) {
            p = 1;
        } else if (((Double) i[20]).doubleValue() <= 3.0) {
            p = WekaGyroClassifier.N389c5a547(i);
        } else if (((Double) i[20]).doubleValue() > 3.0) {
            p = WekaGyroClassifier.N5a8c36d250(i);
        }
        return p;
    }
    static double N389c5a547(Object []i) {
        double p = Double.NaN;
        if (i[5] == null) {
            p = 0;
        } else if (((Double) i[5]).doubleValue() <= -0.060544760143270836) {
            p = WekaGyroClassifier.N5c6eea6e48(i);
        } else if (((Double) i[5]).doubleValue() > -0.060544760143270836) {
            p = 1;
        }
        return p;
    }
    static double N5c6eea6e48(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() <= 2.0) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() > 2.0) {
            p = WekaGyroClassifier.N7edb817e49(i);
        }
        return p;
    }
    static double N7edb817e49(Object []i) {
        double p = Double.NaN;
        if (i[15] == null) {
            p = 0;
        } else if (((Double) i[15]).doubleValue() <= 0.7731865) {
            p = 0;
        } else if (((Double) i[15]).doubleValue() > 0.7731865) {
            p = 1;
        }
        return p;
    }
    static double N5a8c36d250(Object []i) {
        double p = Double.NaN;
        if (i[20] == null) {
            p = 0;
        } else if (((Double) i[20]).doubleValue() <= 7.0) {
            p = WekaGyroClassifier.N17e574cb51(i);
        } else if (((Double) i[20]).doubleValue() > 7.0) {
            p = 0;
        }
        return p;
    }
    static double N17e574cb51(Object []i) {
        double p = Double.NaN;
        if (i[15] == null) {
            p = 0;
        } else if (((Double) i[15]).doubleValue() <= 0.70234215) {
            p = 0;
        } else if (((Double) i[15]).doubleValue() > 0.70234215) {
            p = WekaGyroClassifier.N3c73c1c352(i);
        }
        return p;
    }
    static double N3c73c1c352(Object []i) {
        double p = Double.NaN;
        if (i[13] == null) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() <= 9.0) {
            p = 1;
        } else if (((Double) i[13]).doubleValue() > 9.0) {
            p = 0;
        }
        return p;
    }
}