package com.clean.architecture.invoice.dto.create;

import com.clean.architecture.shared.dto.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceLineCreateDTO {

    private String productDescription;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private GenericDTO invoice;

}
