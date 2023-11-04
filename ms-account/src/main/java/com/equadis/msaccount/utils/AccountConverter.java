package com.equadis.msaccount.utils;

import com.equadis.msaccount.dtos.AccountRecordDto;
import com.equadis.msaccount.entity.EAccount;
import com.equadis.msaccount.model.Account;
import org.springframework.beans.BeanUtils;

public class AccountConverter {

    public static Account dtoToModel(AccountRecordDto dto) {
        var model = new Account();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    public static AccountRecordDto modelToDto(Account model) {
        return new AccountRecordDto(model.getAccountId(), model.getCustomerId(), model.getAccountAmount());
    }

    public static EAccount modelToEntity(Account model) {
        var entity = new EAccount();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public static Account entityToModel(EAccount entity) {
        var model = new Account();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
