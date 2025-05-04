package br.com.lucasmcoelho.chalengeitauspring.controller;

import br.com.lucasmcoelho.chalengeitauspring.dto.TransactionDTO;
import br.com.lucasmcoelho.chalengeitauspring.model.Transaction;
import br.com.lucasmcoelho.chalengeitauspring.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;


@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        if(transactionDTO.getDataHora().isAfter(OffsetDateTime.now()) || transactionDTO.getValor() < 0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        transactionService.addTransaction( new Transaction(transactionDTO.getValor(), transactionDTO.getDataHora()) );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions() {
        transactionService.clearTransactions();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
