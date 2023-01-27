package com.example.BudgetDucklings.service;

import com.example.BudgetDucklings.model.PaymentDetails;
import com.example.BudgetDucklings.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InvoiceService {
    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void registerPayment(PaymentDetails paymentDetails, String username){
        invoiceRepository.store(paymentDetails, username);
    }
    public List<PaymentDetails> getAllPayments(String username){
        return invoiceRepository.findByUsers(username);

    }
}
