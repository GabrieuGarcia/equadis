package com.equadis.msaccount.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    public static Date formatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return sdf.parse(date);
    }


    public static String buildErrorMessage(List<String> errorMessage) {
        StringBuilder finalErrorMessage = new StringBuilder();

        if(!errorMessage.isEmpty() && errorMessage.size() > 1) {
            errorMessage.forEach(error -> { finalErrorMessage.append(error).append("\n"); });
        } else {
            finalErrorMessage.append(errorMessage.get(0));
        }

        return finalErrorMessage.toString();
    }

}
