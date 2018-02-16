package edu.gatech.seclass.sdpcryptogram.services;

import java.util.List;

/**
 * Created by jabbasi on 7/4/17.
 */

public class Utils {

    public static String loginUsername;

    public static boolean isNullOrEmpty(String param) {
        return param == null || param.isEmpty();
    }

    public static boolean isNotNullOrEmpty(String param) {
        return isNullOrEmpty(param) == false;
    }

    public static String[] convertListToArray(List<String> list) {

        if(list == null) {
            return new String[]{};
        }
        String[] array = new String[list.size()];


        array = list.toArray(array);

        return array;
    }


}
