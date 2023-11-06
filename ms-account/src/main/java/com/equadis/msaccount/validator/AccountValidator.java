package com.equadis.msaccount.validator;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.utils.AccountConstants;

import java.util.ArrayList;
import java.util.List;

import static com.equadis.msaccount.utils.Utils.buildErrorMessage;
import static java.math.BigDecimal.ZERO;

public class AccountValidator {

    public static void creation(Account account) throws AccountException {
        List<String> validations = new ArrayList<>();

        validateAmount(account, validations);
        validateCustomer(account, validations);

        if(!validations.isEmpty()) {
            throw new AccountException(buildErrorMessage(validations));
        }
    }

    private static void validateAmount(Account account, List<String> validations) {
        if(account.getAccountAmount().compareTo(ZERO) < 0) {
            validations.add(AccountConstants.MSG_BAD_AMOUNT);
        }
    }

    private static void validateCustomer(Account account, List<String> validations) {
        if(account.getCustomerId() == null) {
            validations.add(AccountConstants.MSG_NO_CUSTOMER);
        }
    }

}
