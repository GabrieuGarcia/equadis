package com.equadis.msaccount.utils;

import com.equadis.msaccount.dtos.TransactionDTO;
import com.equadis.msaccount.dtos.TransactionRecordDTO;
import com.equadis.msaccount.entity.ETransaction;
import com.equadis.msaccount.model.Transaction;
import org.springframework.beans.BeanUtils;

public class TransactionConverter {

    public static Transaction dtoToModel(TransactionRecordDTO dto) {
        if (dto == null) {
            return null;
        }

        var model = new Transaction();
        BeanUtils.copyProperties(dto, model);

        //Add other Objects
        model.setAccount(AccountConverter.dtoToModel(dto.account()));
        return model;
    }

    public static TransactionDTO modelToDto(Transaction model) {
        if (model == null) {
            return null;
        }

        var dto = new TransactionDTO();
        BeanUtils.copyProperties(model, dto);

        //Add other Objects
        dto.setAccount(AccountConverter.modelToDto(model.getAccount()));
        return dto;
    }

    public static ETransaction modelToEntity(Transaction model) {
        if (model == null) {
            return null;
        }

        var entity = new ETransaction();
        BeanUtils.copyProperties(model, entity);

        //Add other Objects
        entity.setAccount(AccountConverter.modelToEntity(model.getAccount()));
        return entity;
    }

    public static Transaction entityToModel(ETransaction entity) {
        if (entity == null) {
            return null;
        }

        var model = new Transaction();
        BeanUtils.copyProperties(entity, model);

        //Add other Objects
        model.setAccount(AccountConverter.entityToModel(entity.getAccount()));
        return model;
    }

}
