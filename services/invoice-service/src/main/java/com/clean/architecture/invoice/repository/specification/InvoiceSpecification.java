package com.clean.architecture.invoice.repository.specification;

import com.clean.architecture.invoice.dto.filter.InvoiceFilterDTO;
import com.clean.architecture.invoice.model.Invoice;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecification {

    public static Specification<Invoice> matching(InvoiceFilterDTO filter) {
        return (root, query, cb) -> {
            Predicate predicates = cb.conjunction();

            if (filter.getInvoiceNumber() != null && !filter.getInvoiceNumber().isEmpty()) {
                predicates = cb.and(predicates, cb.equal(root.get("invoiceNumber"), filter.getInvoiceNumber()));
            }
            if (filter.getCustomerName() != null && !filter.getCustomerName().isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("customerName")), "%" + filter.getCustomerName().toLowerCase() + "%"));
            }
            if (filter.getCustomerEmail() != null && !filter.getCustomerEmail().isEmpty()) {
                predicates = cb.and(predicates, cb.like(cb.lower(root.get("customerEmail")), "%" + filter.getCustomerEmail().toLowerCase() + "%"));
            }
            if (filter.getTotalAmount() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("totalAmount"), filter.getTotalAmount()));
            }
            if (filter.getStatusId() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("status").get("id"), filter.getStatusId()));
            }
            return predicates;
        };
    }

}
