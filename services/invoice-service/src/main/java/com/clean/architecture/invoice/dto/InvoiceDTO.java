package com.clean.architecture.invoice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private BigDecimal totalAmount;
    private InvoiceStatusDTO status;

}
