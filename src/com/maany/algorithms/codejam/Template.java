package com.maany.algorithms.codejam;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * use when first line of input contains no of test cases T
 * Created by Maany on 4/11/15.
 */
public abstract class Template {
    static Scanner in;
    static String input;
    static String output;
    static PrintWriter out;
    static int currentTestCase = 0;
    static int noOfTestCases = 0;

    public void initInput() throws FileNotFoundException {
        in = new Scanner(new FileReader(input));
        noOfTestCases = in.nextInt();
        System.out.println("No of Test Cases : " + noOfTestCases); //TODO comment out output

    }

    /**
     * Define this method to read test case as defined in the problem statement
     */
    public abstract void readNextTestCase();

    /**
     * init Scanner and read the first input. use {@link #readNextTestCase()} to read subsequent inputs.
     * This is the first method to be called after defining {@link #readNextTestCase()} and Constructor
     *
     * @return current test case
     */
    public int getData() {
        try {
            initInput();
            readNextTestCase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return currentTestCase;
    }

    public void caseOutput() {

    }
}
