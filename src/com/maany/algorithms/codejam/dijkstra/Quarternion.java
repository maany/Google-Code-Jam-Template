package com.maany.algorithms.codejam.dijkstra;

/**
 * Created by maany on 4/11/15.
 */
public class Quarternion {
    public enum Values {
        i, k, j, one;
    }

    public QuarternionValueWrapper minusOne, minusI, minusJ, minusK, plusOne, plusI, plusJ, plusK;
    private QuarternionValueWrapper[][] table = new QuarternionValueWrapper[4][4];

    public Quarternion() {
        minusI = new QuarternionValueWrapper(Values.i, -1);
        minusJ = new QuarternionValueWrapper(Values.j, -1);
        minusK = new QuarternionValueWrapper(Values.k, -1);
        minusOne = new QuarternionValueWrapper(Values.one, -1);
        plusI = new QuarternionValueWrapper(Values.i, 1);
        plusJ = new QuarternionValueWrapper(Values.j, 1);
        plusK = new QuarternionValueWrapper(Values.k, 1);
        plusOne = new QuarternionValueWrapper(Values.one, 1);
        table[0][0] = plusOne;
        table[0][1] = plusI;
        table[0][2] = plusJ;
        table[0][3] = plusK;
        table[1][0] = plusI;
        table[1][1] = minusOne;
        table[1][2] = plusK;
        table[1][3] = minusJ;
        table[2][0] = plusJ;
        table[2][1] = minusK;
        table[2][2] = minusOne;
        table[2][3] = plusI;
        table[3][0] = plusK;
        table[3][1] = plusJ;
        table[3][2] = minusI;
        table[3][3] = minusOne;
    }

    private int mapValueToDataSet(Values v) {
        int x = -1;
        switch (v) {
            case i:
                x = 1;
                break;
            case k:
                x = 3;
                break;
            case j:
                x = 2;
                break;
            case one:
                x = 0;
        }
        return x;
    }

    public QuarternionValueWrapper multiply(QuarternionValueWrapper v1, QuarternionValueWrapper v2) {
        int x, y;
        x = mapValueToDataSet(v1.getValue());
        y = mapValueToDataSet(v2.getValue());
        if (v1.getSign() != v2.getSign())
            return v1.invert(table[x][y]);
        return table[x][y];
    }

    class QuarternionValueWrapper {
        private Values value;
        private int sign;

        QuarternionValueWrapper(Values value, int sign) {
            this.value = value;
            this.sign = sign;
        }

        public Values getValue() {
            return value;
        }

        public void setValue(Values value) {
            this.value = value;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }

        @Override
        public boolean equals(Object obj) {
            QuarternionValueWrapper value = (QuarternionValueWrapper) obj;
            if (this.getSign() == value.getSign() && this.getValue() == value.getValue())
                return true;
            else
                return false;
        }

        public QuarternionValueWrapper invert(QuarternionValueWrapper value) {
            if (value == plusOne)
                return minusOne;
            else if (value == plusK)
                return minusK;
            else if (value == plusJ)
                return minusJ;
            else if (value == plusI)
                return minusI;
            else if (value == minusOne)
                return plusOne;
            else if (value == minusI)
                return plusI;
            else if (value == minusJ)
                return plusJ;
            else
                return plusK;

        }
    }
}
