package com.equadis.msaccount.utils;

import com.equadis.msaccount.dtos.TransactionDTO;
import com.equadis.msaccount.dtos.TransactionRecordDTO;
import com.equadis.msaccount.entity.ETransaction;
import com.equadis.msaccount.model.Transaction;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionConverter {

    public static Transaction recordDtoToModel(TransactionRecordDTO dto) {
        if (dto == null) {
            return null;
        }

        var model = new Transaction();
        BeanUtils.copyProperties(dto, model);

        //Add other Objects
        model.setAccount(AccountConverter.recordDtoToModel(dto.account()));
        return model;
    }

    public static TransactionRecordDTO modelToRecordDto(Transaction model) {
        if (model == null) {
            return null;
        }

        var dto = new TransactionRecordDTO(
                model.getTransactionId(),
                AccountConverter.modelToRecordDto(model.getAccount()),
                model.getTransactionAmount(),
                model.getTransactionDate(),
                model.getTransactionType());

        return dto;
    }

    public static TransactionDTO modelToDto(Transaction model) {
        if (model == null) {
            return null;
        }

        var dto = new TransactionDTO();
        BeanUtils.copyProperties(model, dto);

        //Add other Objects
        dto.setAccount(AccountConverter.modelToRecordDto(model.getAccount()));
        return dto;
    }

    public static List<TransactionDTO> modelsToDtos(List<Transaction> models) {
        if (models == null || models.isEmpty()) {
            return null;
        }

        List<TransactionDTO> dtos = new ArrayList<>();

        models.forEach(model -> {
            var dto = new TransactionDTO();
            BeanUtils.copyProperties(model, dto);

            //Add other Objects
            dto.setAccount(AccountConverter.modelToRecordDto(model.getAccount()));
            dtos.add(dto);
        });

        return dtos;
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

    public static List<Transaction> entitiesToModels(List<ETransaction> entities) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }

        List<Transaction> models = new ArrayList<>();

        entities.forEach(entity -> {
            var model = new Transaction();
            BeanUtils.copyProperties(entity, model);

            //Add other Objects
            model.setAccount(AccountConverter.entityToModel(entity.getAccount()));
            models.add(model);
        });

        return models;
    }

}
