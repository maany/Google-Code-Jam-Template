package com.maany.algorithms.codejam;

import java.io.FileNotFoundException;

/**
 * Created by maany on 4/11/15.
 */
public class QStandingOvation extends Template {
    /* Variable declaration*/
    private static int smax;
    private int contSum;
    private int requiredPeople;

    public QStandingOvation() {
        super();
    }

    public static void main(String args[]) {
        input = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\standing_ovation.txt";
        output = "";
        QStandingOvation problem = new QStandingOvation();
        try {
            problem.initInput();

            for (currentTestCase = 1; currentTestCase <= noOfTestCases; currentTestCase++) {
                problem.readNextTestCase();
                System.out.println("Case #" + currentTestCase + ": " + problem.requiredPeople);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    @Override
    public void readNextTestCase() {
        smax = in.nextInt();
        contSum = 0;
        requiredPeople = 0;

        for (int i = 0; i <= smax; i++) {
            int noOfPeople = in.nextInt();
            if (i > contSum && noOfPeople != 0)
                requiredPeople += i - contSum;
            contSum += noOfPeople + requiredPeople;
        }
    }
}
