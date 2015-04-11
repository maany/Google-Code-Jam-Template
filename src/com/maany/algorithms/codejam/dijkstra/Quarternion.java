package com.maany.algorithms.codejam.dijkstra;

/**
 * Created by maany on 4/11/15.
 */
public class Quarternion {
    public enum Values {
        i, k, j, one;

        private int sign = 1;
        String message = "Test";

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }

    public static Values minusOne, minusI, minusJ, minusK;
    private static Values[][] table = new Values[4][4];

    static {
        minusOne = Values.one;
        minusOne.setSign(-1);
        minusI = Values.i;
        minusI.setSign(-1);
        minusJ = Values.j;
        minusJ.setSign(-1);
        minusK = Values.k;
        minusK.setSign(-1);
        Values.i.message = "Value changed for i. Check everywhere";
        table[0][0] = Values.one;
        table[0][1] = Values.i;
        table[0][2] = Values.j;
        table[0][3] = Values.k;
        table[1][0] = Values.i;
        table[1][1] = minusOne;
        table[1][2] = Values.k;
        table[1][3] = minusJ;
        table[2][0] = Values.j;
        table[2][1] = minusK;
        table[2][2] = minusOne;
        table[2][3] = Values.i;
        table[3][0] = Values.k;
        table[3][1] = Values.j;
        table[3][2] = minusI;
        table[3][3] = minusOne;
    }

    private static int mapValueToDataSet(Values v) {
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

    public static Values multiply(Values v1, Values v2) {
        int x, y;
        x = mapValueToDataSet(v1);
        y = mapValueToDataSet(v2);
        return table[x][y];
    }
}
