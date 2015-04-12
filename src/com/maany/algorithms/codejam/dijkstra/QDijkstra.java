package com.maany.algorithms.codejam.dijkstra;

import com.maany.algorithms.codejam.Template;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by OPSKMC on 4/11/15.
 */
public class QDijkstra extends Template<String> {
    String result;
    Quarternion quarternion;

    public static void main(String[] args) {
        input = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\dijkstra\\C-small-attempt0.in";
        output = "E:\\Projects\\Google Code Jam\\src\\com\\maany\\algorithms\\codejam\\dijkstra\\djikstra.out";
        final QDijkstra problem = new QDijkstra();
        problem.quarternion = new Quarternion();
        try {
            problem.initInputOutput();
            Runnable run1 = new Runnable() {
                @Override
                public void run() {
                    for (int currentTestCase = 1; currentTestCase <= 25; currentTestCase++) {
                        problem.readNextTestCase();
                        problem.caseFileOutput(problem.result);
                    }
                }
            };
            Runnable run2 = new Runnable() {
                @Override
                public void run() {
                    for (int currentTestCase = 26; currentTestCase <= 50; currentTestCase++) {
                        problem.readNextTestCase();
                        System.out.println("Case #" + currentTestCase + ": " + problem.result);
                        //problem.caseFileOutput(problem.result);
                    }
                }
            };
            new Thread(run1).start();
            new Thread(run2).start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            //in.close();
            // out.close();
        }
    }

    @Override
    public void readNextTestCase() {
        result = "NO";
        quarternion = new Quarternion();
        int K = in.nextInt();
        int L = in.nextInt();
        ArrayList<Integer> iList = new ArrayList<Integer>();
        ArrayList<Integer> kList = new ArrayList<Integer>();
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
        //System.out.println("Case String : " + caseString); //TODO remove
        // populate the iList and kList
        Quarternion.QuarternionValueWrapper cumulativeIValue = charToValuesMapper(caseString.charAt(0));
        Quarternion.QuarternionValueWrapper cumulativeKValue = charToValuesMapper(caseString.charAt(caseString.length() - 1));
        if (cumulativeIValue == quarternion.plusI)
            iList.add(0);
        if (cumulativeKValue == quarternion.plusK)
            kList.add(caseString.length() - 1);
        for (int i = 1, k = caseString.length() - 2; (i < caseString.length() && k >= 0); i++, k--) {
            cumulativeIValue = quarternion.multiply(cumulativeIValue, charToValuesMapper(caseString.charAt(i)));
            cumulativeKValue = quarternion.multiply(charToValuesMapper(caseString.charAt(k)), cumulativeKValue);
            if (cumulativeIValue == quarternion.plusI)
                iList.add(i);
            if (cumulativeKValue == quarternion.plusK)
                kList.add(k);
        }
        /* find if the remaining substring equates to j*/
        for (int l = 0; l < iList.size(); l++)
            for (int m = 0; m < kList.size(); m++) {
                if (iList.get(l) + 1 < kList.get(m)) {

                    String currentString = caseString.substring(iList.get(l) + 1, kList.get(m));
                    //System.out.println("Current substring : " + currentString + " Case : " + currentTestCase); //TODO remove
                    if (getSubStringValue(currentString) == quarternion.plusJ) {
                        result = "YES";
                        return;
                    }
                }
            }


    }

    private Quarternion.QuarternionValueWrapper charToValuesMapper(char ch) {
        Quarternion.QuarternionValueWrapper value = null;
        switch (ch) {
            case '1':
                value = quarternion.plusOne;
                break;
            case 'i':
                value = quarternion.plusI;
                break;
            case 'j':
                value = quarternion.plusJ;
                break;
            case 'k':
                value = quarternion.plusK;
                break;
        }
        return value;
    }

    private Quarternion.QuarternionValueWrapper getSubStringValue(String string) {
        if (string.length() == 0)
            return quarternion.plusOne;
        Quarternion.QuarternionValueWrapper value = charToValuesMapper(string.charAt(0));
        for (int i = 1; i < string.length(); i++) {
            value = quarternion.multiply(value, charToValuesMapper(string.charAt(i)));
        }
        return value;
    }
}
