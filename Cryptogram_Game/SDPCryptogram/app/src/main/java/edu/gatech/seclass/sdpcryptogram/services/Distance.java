package edu.gatech.seclass.sdpcryptogram.services;

public class Distance {
    public static StringBuilder Shifter(String solution, String encoded) {
    StringBuilder result = new StringBuilder(solution.length());
    for (int i = 0; i < solution.length(); i++) {
        if(Character.isLetter(solution.toLowerCase().charAt(i))) {
            int difference = solution.toLowerCase().charAt(i) - encoded.toLowerCase().charAt(i);
            if (difference < 0){
                difference += 26;
            }
            result.append(difference+",");
        }
        else {result.append("0,");
        }
    }return result;
    }
}
