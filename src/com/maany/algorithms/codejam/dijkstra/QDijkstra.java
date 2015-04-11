package com.maany.algorithms.codejam.dijkstra;

import com.maany.algorithms.codejam.Template;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by OPSKMC on 4/11/15.
 */
public class QDijkstra extends Template<String> {
    String result;
    QDijkstra problem;
    ArrayList<Integer> iList = new ArrayList<Integer>();
    ArrayList<Integer> kList = new ArrayList<Integer>();

    public static void main(String[] args) {
        input = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\dijkstra\\dijkstra_input.txt";
        output = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\standing_ovation_output.out";
        QDijkstra problem = new QDijkstra();
        try {
            problem.initInputOutput();
            for (currentTestCase = 1; currentTestCase <= noOfTestCases; currentTestCase++) {
                problem.readNextTestCase();
                problem.caseConsoleOutput(problem.result);
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
        result = "NO";
        int K = in.nextInt();
        int L = in.nextInt();
        int flag = 0;
        System.out.println();
        if (K * L < 3) {
            in.next();
            return;
        }
        String base = in.next();
        // Construct the caseString based on K and L
        String caseString = "";
        for (int i = 0; i < L; i++) {
            caseString += base;
        }
        System.out.println("Case String : " + caseString); //TODO remove
        // populate the iList and kList
        Quarternion.Values cumulativeIValue = charToValuesMapper(caseString.charAt(0));
        Quarternion.Values cumulativeKValue = charToValuesMapper(caseString.charAt(caseString.length() - 1));
        if (cumulativeIValue == Quarternion.Values.i && cumulativeIValue.getSign() == 1)
            iList.add(0);
        if (cumulativeKValue == Quarternion.Values.k && cumulativeIValue.getSign() == 1)
            kList.add(caseString.length() - 1);
        for (int i = 1, k = caseString.length() - 2; i < caseString.length() && k >= 0; i++, k--) {
            cumulativeIValue = Quarternion.multiply(cumulativeIValue, charToValuesMapper(caseString.charAt(i)));
            cumulativeKValue = Quarternion.multiply(cumulativeKValue, charToValuesMapper(caseString.charAt(k)));
            if (cumulativeIValue == Quarternion.Values.i && cumulativeIValue.getSign() == 1)
                iList.add(i);
            if (cumulativeKValue == Quarternion.Values.k && cumulativeIValue.getSign() == 1)
                kList.add(k);
        }
        /* find if the remaining substring equates to j*/
        for (int l = 0; l < iList.size(); l++)
            for (int m = 0; m < kList.size(); m++) {
                String currentString = caseString.substring(iList.get(l) + 1, kList.get(m));
                if (getSubStringValue(currentString) == Quarternion.Values.j) {
                    result = "YES";
                    return;
                }
            }
    }

    private Quarternion.Values charToValuesMapper(char ch) {
        Quarternion.Values value = null;
        switch (ch) {
            case '1':
                value = Quarternion.Values.one;
                break;
            case 'i':
                value = Quarternion.Values.i;
                break;
            case 'j':
                value = Quarternion.Values.j;
                break;
            case 'k':
                value = Quarternion.Values.k;
                break;
        }
        return value;
    }

    private Quarternion.Values getSubStringValue(String string) {
        Quarternion.Values value = charToValuesMapper(string.charAt(0));
        for (int i = 1; i < string.length(); i++) {
            value = Quarternion.multiply(value, charToValuesMapper(string.charAt(i)));
        }
        return value;
    }
}