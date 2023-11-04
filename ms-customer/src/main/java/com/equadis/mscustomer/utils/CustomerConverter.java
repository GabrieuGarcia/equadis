package com.equadis.mscustomer.utils;

import com.equadis.mscustomer.dto.CustomerRecordDto;
import com.equadis.mscustomer.entity.ECustomer;
import com.equadis.mscustomer.model.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerConverter {

    public static Customer dtoToModel(CustomerRecordDto dto) {
        if(dto == null) {
            return null;
        }

        var model = new Customer();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    public static CustomerRecordDto modelToDto(Customer model) {
        if(model == null) {
            return null;
        }

        return new CustomerRecordDto(model.getCustomerId(), model.getCustomerName());
    }

    public static ECustomer modelToEntity(Customer model) {
        if(model == null) {
            return null;
        }

        var entity = new ECustomer();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public static Customer entityToModel(ECustomer entity) {
        if(entity == null) {
            return null;
        }

        var model = new Customer();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
