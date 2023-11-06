package com.equadis.mscustomer.enpoint;

import com.equadis.mscustomer.dto.CustomerRecordDto;
import com.equadis.mscustomer.service.CustomerService;
import com.equadis.mscustomer.utils.CustomerConverter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerEndpoint {

    private final CustomerService customerService;

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/save")
    public ResponseEntity<CustomerRecordDto> save(@RequestBody @Valid CustomerRecordDto customerRecordDto) {

        var response = this.customerService.save(CustomerConverter.dtoToModel(customerRecordDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerConverter.modelToDto(response));
    }
}
