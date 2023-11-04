package com.equadis.msaccount.validator;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.utils.Constants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;

public class AccountValidator {

    public static void creation(Account account) throws AccountException {
        List<String> validations = new ArrayList<>();

        validateAmount(account, validations);
        validateCustomer(account, validations);

        if(validations != null || !validations.isEmpty()) {
            throw new AccountException(buildErrorMessage(validations));
        }
    }

    private static void validateAmount(Account account, List<String> validations) {
        if(account.getAccountAmount().compareTo(ZERO) <= 0) {
            validations.add(Constants.MSG_BAD_AMOUNT);
        }
    }

    private static void validateCustomer(Account account, List<String> validations) {
        if(account.getCustomerId() == null) {
            validations.add(Constants.MSG_NO_CUSTOMER);
        }
    }

    private static String buildErrorMessage(List<String> errorMessage) {
        StringBuilder finalErrorMessage = new StringBuilder();
        if(errorMessage.size() > 1) {
            errorMessage.forEach(error -> { finalErrorMessage.append(error).append("\n"); });
        } else {
            finalErrorMessage.append(errorMessage.get(0));
        }
        return finalErrorMessage.toString();
    }

}
