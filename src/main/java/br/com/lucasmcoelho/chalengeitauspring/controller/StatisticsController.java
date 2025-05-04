package br.com.lucasmcoelho.chalengeitauspring.controller;

import br.com.lucasmcoelho.chalengeitauspring.dto.StatisticsDTO;
import br.com.lucasmcoelho.chalengeitauspring.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

     @GetMapping
     public ResponseEntity<StatisticsDTO> getStatistics() {
         return ResponseEntity.ok(new StatisticsDTO(transactionService.getStatistics()));
     }
}
