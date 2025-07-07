package com.clean.architecture.invoice.dto.modify;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceLineModifyDTO {

    private Long id;
    private String productDescription;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

}
