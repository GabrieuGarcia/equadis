package com.equadis.msaccount.utils;

import com.equadis.msaccount.dtos.AccountRecordDto;
import com.equadis.msaccount.entity.EAccount;
import com.equadis.msaccount.model.Account;
import org.springframework.beans.BeanUtils;

public class AccountConverter {

    public static Account recordDtoToModel(AccountRecordDto dto) {
        if(dto == null) {
            return null;
        }

        var model = new Account();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    public static AccountRecordDto modelToRecordDto(Account model) {
        if(model == null) {
            return null;
        }

        return new AccountRecordDto(model.getAccountId(), model.getCustomerId(), model.getAccountAmount());
    }

    public static EAccount modelToEntity(Account model) {
        if(model == null) {
            return null;
        }

        var entity = new EAccount();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public static Account entityToModel(EAccount entity) {
        if(entity == null) {
            return null;
        }

        var model = new Account();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
