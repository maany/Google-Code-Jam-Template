package com.maany.algorithms.codejam;

import java.io.FileNotFoundException;

/**
 * Created by maany on 4/11/15.
 */
public class QStandingOvation extends Template<Integer> {
    /* Variable declaration*/
    private static int smax;
    private int contSum;
    private int requiredPeople;

    public QStandingOvation() {
        super();
    }

    public static void main(String args[]) {
        input = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\A-small-attempt0.in";
        output = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\standing_ovation_output.out";
        QStandingOvation problem = new QStandingOvation();
        try {
            problem.initInputOutput();

            for (currentTestCase = 1; currentTestCase <= noOfTestCases; currentTestCase++) {
                problem.readNextTestCase();
                problem.caseConsoleOutput(problem.requiredPeople);
                problem.caseFileOutput(problem.requiredPeople);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
        }
    }

    @Override
    public void readNextTestCase() {
        smax = in.nextInt();
        contSum = 0;
        requiredPeople = 0;
        String currentPeople = in.next();
        int[] noOfPeoples = new int[currentPeople.length()];
        for (int i = 0; i < currentPeople.length(); i++) {
            noOfPeoples[i] = currentPeople.charAt(i) - '0';
        }
        for (int i = 0; i <= smax; i++) {
            int noOfPeople = noOfPeoples[i];
            if (i > contSum && noOfPeople != 0)
                requiredPeople += i - contSum;
            contSum += noOfPeople + requiredPeople;
        }
    }
}
