package com.equadis.msaccount.enpoint;

import com.equadis.msaccount.exceptions.AccountException;
import com.equadis.msaccount.model.Account;
import com.equadis.msaccount.service.AccountService;
import com.equadis.msaccount.utils.AccountConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountEndpointTest {

    @InjectMocks
    private AccountEndpoint endpoint;

    @Mock
    private AccountService accountService;

    private Account buildAccount() {
        var account = new Account();
        account.setAccountAmount(new BigDecimal(10000));
        account.setCustomerId(UUID.randomUUID());
        return account;
    }

    @Test
    void saveTest() throws AccountException {
        //Given
        final var account = buildAccount();
        var dto = AccountConverter.modelToRecordDto(account);
        when(accountService.save(any())).thenReturn(account);

        //When
        var result = this.endpoint.save(dto);

        //Then
        verify(accountService, atLeast(1)).save(any());
        Assertions.assertNotNull(result.getBody());
    }

}