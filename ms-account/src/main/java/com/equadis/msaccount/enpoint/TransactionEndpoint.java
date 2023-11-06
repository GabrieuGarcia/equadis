package com.equadis.msaccount.enpoint;

import com.equadis.msaccount.dtos.TransactionDTO;
import com.equadis.msaccount.dtos.TransactionRecordDTO;
import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.exceptions.TransactiontException;
import com.equadis.msaccount.service.TransactionService;
import com.equadis.msaccount.utils.TransactionConverter;
import com.equadis.msaccount.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionEndpoint {
    private final TransactionService transactionService;

    public TransactionEndpoint(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody TransactionRecordDTO dto) throws TransactiontException, AccountException, ParseException {

        var response = this.transactionService.deposit(TransactionConverter.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelToDto(response));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(@RequestBody TransactionRecordDTO dto) throws TransactiontException, AccountException, ParseException {

        var response = this.transactionService.withdraw(TransactionConverter.dtoToModel(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelToDto(response));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TransactionDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelsToDtos(this.transactionService.findAll()));
    }


    @GetMapping("/search/accountId")
    public ResponseEntity<List<TransactionDTO>> findAllByAccountId(@RequestParam final UUID accountId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelsToDtos(this.transactionService.findTransactionsByAccountId(accountId)));
    }

    @GetMapping("/search/amounts")
    public ResponseEntity<List<TransactionDTO>> findAllByAmounts(@RequestParam final BigDecimal firstAmount,
                                                                 @RequestParam final BigDecimal secondAmount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionConverter.modelsToDtos(this.transactionService.findBetweenAmounts(firstAmount, secondAmount)));
    }

    @GetMapping("/search/dates")
    public ResponseEntity<List<TransactionDTO>> findAllByDates(@RequestParam final String firstDate,
                                                                 @RequestParam final String secondDate) throws ParseException {

        var result = TransactionConverter.modelsToDtos(this.transactionService.findBetweenDates(Utils.formatDate(firstDate), Utils.formatDate(secondDate)));
        return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }

}
