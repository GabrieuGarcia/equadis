package com.equadis.msaccount.service;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;

import java.util.UUID;

public interface AccountService {

    Account save(Account account) throws AccountException;

    Account findById(UUID accountId);
}
