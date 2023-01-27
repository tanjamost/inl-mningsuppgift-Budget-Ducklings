package com.example.BudgetDucklings.controller;

import com.example.BudgetDucklings.model.PaymentDetails;
import com.example.BudgetDucklings.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public String showInvoiceTable(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/home";
        } else {
            List<PaymentDetails> payments = invoiceService.getAllPayments(username);
            model.addAttribute("payments", payments);
            return "Invoice";
        }
    }

    @GetMapping("/new")
    public String createPayment(@ModelAttribute("payment") PaymentDetails paymentDetails, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/home";
        } else {
            return "Payment";
        }

    }

    @PostMapping("/new")
    public String addPayment(HttpSession session, @ModelAttribute("payment") PaymentDetails paymentDetails) {
        String username = (String) session.getAttribute("username");

        invoiceService.registerPayment(paymentDetails, username);

        return "redirect:/invoice";
    }

}