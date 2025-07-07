package com.clean.architecture.invoice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvoiceStatusEnum {

    CREATED(1L, "CREATED", "Creada"),
    SENT(2L, "SENT", "Enviada"),
    PAID(3L, "PAID", "Pagada");

    private final Long id;
    private final String code;
    private final String description;

}
