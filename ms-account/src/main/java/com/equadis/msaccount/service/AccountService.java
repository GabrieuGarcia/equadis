package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;

public interface AccountService {

    Account save(Account account) throws AccountException;

}
