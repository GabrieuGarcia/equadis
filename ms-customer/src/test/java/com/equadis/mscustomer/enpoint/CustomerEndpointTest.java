package com.equadis.mscustomer.enpoint;

import com.equadis.mscustomer.model.Customer;
import com.equadis.mscustomer.service.CustomerService;
import com.equadis.mscustomer.utils.CustomerConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerEndpointTest {

    @InjectMocks
    private CustomerEndpoint endpoint;

    @Mock
    private CustomerService customerService;

    private Customer buildCustomer() {
        var customer = new Customer();
        customer.setCustomerId(UUID.randomUUID());
        customer.setCustomerName("Gabriel Garcia");
        return customer;
    }

    @Test
    void saveTest() {
        //Given
        final var customer = buildCustomer();
        var dto = CustomerConverter.modelToDto(customer);
        when(customerService.save(any())).thenReturn(customer);

        //When
        var result = this.endpoint.save(dto);

        //Then
        verify(customerService, atLeast(1)).save(any());
        Assertions.assertNotNull(result.getBody());
    }

}