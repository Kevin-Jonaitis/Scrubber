package com.company.redacted;

import java.io.*;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.json.*;

/*
 * If you need more classes, simply define them inline.
 */

class Scrub {
  public static void main(String[] args) throws IOException {
    runTestCases();
  }


  public static String scrub(String data) {
    JSONObject inputObject = new JSONObject(data);

    // implement scrubbing here

    return inputObject.toString();
  }

  

  // runs all test cases in the examples 
  private static void runTestCases() {
    String[] listOfExamples = new File("./../examples/raw").list();
    for (String fileName : listOfExamples) {
      
      try {
        String input = readFile("./../examples/raw/" + fileName);
        String expected = readFile("./../examples/scrubbed/" + fileName);
        String result = new JSONObject(input).toString();

        if (result != expected) {
          System.out.println("Expected \n\n" + result + "\n\n to be \n\n" + expected);
        }

      } catch (IOException e) {
        System.out.println("Could not find test case file '" + fileName + "'");
      }
    }
  }


  // helper for reading in example files
  public static String readFile(String fileName)
    throws IOException 
  {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        return sb.toString();
    } finally {
        br.close();
    }
  }

}
