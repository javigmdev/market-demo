package com.clean.architecture.invoice.dto.filter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceFilterDTO {

    private String invoiceNumber;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private BigDecimal totalAmount;
    private Long statusId;

    @Min(value = 0, message = "Page index must not be less than zero")
    private int page;

    @Min(value = 1, message = "Page size must not be less than one")
    @Max(value = 100, message = "Page size must not be greater than 100")
    private int size;

}
