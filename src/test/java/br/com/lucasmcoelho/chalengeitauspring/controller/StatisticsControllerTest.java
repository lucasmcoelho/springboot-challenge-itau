package br.com.lucasmcoelho.chalengeitauspring.controller;

import br.com.lucasmcoelho.chalengeitauspring.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.DoubleSummaryStatistics;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatisticsController.class)
class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testGetStatistics_ReturnsStatisticsDTO() throws Exception {
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        stats.accept(100.0);
        stats.accept(200.0);

        Mockito.when(transactionService.getStatistics()).thenReturn(stats);

        mockMvc.perform(get("/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2))
                .andExpect(jsonPath("$.sum").value(300.0))
                .andExpect(jsonPath("$.avg").value(150.0))
                .andExpect(jsonPath("$.min").value(0.0))
                .andExpect(jsonPath("$.max").value(200.0));
    }
}