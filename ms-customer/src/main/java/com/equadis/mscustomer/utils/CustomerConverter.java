package com.equadis.mscustomer.utils;

import com.equadis.mscustomer.dtos.CustomerRecordDto;
import com.equadis.mscustomer.model.Customer;
import com.equadis.mscustomer.entities.ECustomer;
import org.springframework.beans.BeanUtils;

public class CustomerConverter {

    public static Customer dtoToModel(CustomerRecordDto dto) {
        var model = new Customer();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    public static CustomerRecordDto modelToDto(Customer model) {
        return new CustomerRecordDto(model.getCustomerId(), model.getCustomerName());
    }

    public static ECustomer modelToEntity(Customer model) {
        var entity = new ECustomer();
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public static Customer entityToModel(ECustomer entity) {
        var model = new Customer();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}
