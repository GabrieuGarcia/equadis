package com.equadis.msaccount.utils;

import java.util.List;

public class Utils {

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
