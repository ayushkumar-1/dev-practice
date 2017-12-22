package com.test.swiggy;

/* Save this in a file called Main.java to compile and test it */

/* Do not add a package declaration */
import java.util.*;
import java.io.*;

/* You may add any imports here, if you wish, but only from the 
   standard library */

public class Main {
    public static int processData(ArrayList<String> array) {
        /* 
         * Modify this method to process `array` as indicated
         * in the question. At the end, return the appropriate value.
         *
         * Please create appropriate classes, and use appropriate
         * data structures as necessary.
         *
         * Do not print anything in this method.
         *
         * Submit this entire program (not just this method)
         * as your answer
         */
    	int maxDept = 0;
    	int minSal = Integer.MAX_VALUE;
    	for(int i = 0; i < array.size(); i++){
    		
    		String[] str = array.get(i).split("\\,");
    		if(Integer.parseInt(str[2].trim()) >= maxDept){
    			if(Integer.parseInt(str[3].trim()) <= minSal){
    				minSal = Integer.parseInt(str[3].trim());
    			}
    			maxDept = Integer.parseInt(str[2].trim());
    		}
    	}
        return minSal;
    }

    public static void main (String[] args) {
        ArrayList<String> inputData = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("src/input.txt")));
            while(in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (!line.isEmpty()) // Ignore blank lines
                    inputData.add(line);
            }
            int retVal = processData(inputData);
            System.out.println(retVal);
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("src/output.txt")));
            output.println("" + retVal);
            output.close();
        } catch (IOException e) {
            System.out.println("IO error in input.txt or output.txt");
        }
    }
}

