package br.com.lucasmcoelho.chalengeitauspring.controller;

import br.com.lucasmcoelho.chalengeitauspring.dto.TransactionDTO;
import br.com.lucasmcoelho.chalengeitauspring.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testAddTransaction_ValidTransaction_ReturnsCreated() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO(100.0, OffsetDateTime.now().minusMinutes(1));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valor\": 100.0, \"dataHora\": \"" + transactionDTO.getDataHora() + "\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testAddTransaction_InvalidTransaction_ReturnsUnprocessableEntity() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO(-100.0, OffsetDateTime.now().plusMinutes(1));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valor\": -100.0, \"dataHora\": \"" + transactionDTO.getDataHora() + "\"}"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testClearTransactions_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isNoContent());

        Mockito.verify(transactionService).clearTransactions();
    }
}
