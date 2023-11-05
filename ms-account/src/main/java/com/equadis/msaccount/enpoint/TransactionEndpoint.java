package com.equadis.msaccount.enpoint;

import com.equadis.msaccount.dtos.TransactionDTO;
import com.equadis.msaccount.dtos.TransactionRecordDTO;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.service.TransactionService;
import com.equadis.msaccount.utils.TransactionConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionEndpoint {
    private final TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody TransactionRecordDTO dto) throws TransactiontException, AccountException {

        var response = this.transactionService.deposit(TransactionConverter.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelToDto(response));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody TransactionRecordDTO dto) throws TransactiontException, AccountException {

        var response = this.transactionService.withdraw(TransactionConverter.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelToDto(response));
    }

}
