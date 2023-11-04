package com.equadis.msaccount.enpoint;

import com.equadis.msaccount.dtos.AccountRecordDto;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.service.AccountService;
import com.equadis.msaccount.utils.AccountConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountEndpoint {

    private final AccountService accountService;

    public AccountEndpoint(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save")
    public ResponseEntity<AccountRecordDto> save(@RequestBody AccountRecordDto accountRecordDto) throws AccountException {

        var response = this.accountService.save(AccountConverter.dtoToModel(accountRecordDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(AccountConverter.modelToDto(response));
    }

}
