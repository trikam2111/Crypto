package com.recruitment.crypto.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CryptoControllerTests {

    @Autowired
    private MockMvc mockMvc;

   @Test
    public void getRatesTest() throws Exception {
        this.mockMvc.perform(get("/currencies/BTC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.source",is("BTC")));
    }

    @Test
    public void getRatesWithFilterTest() throws Exception {
       this.mockMvc.perform(get("/currencies/BTC?filter=USD,ETH"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.USD").exists())
               .andExpect(jsonPath("$.ETH").exists())
               .andExpect(jsonPath("$.source", is("BTC")));
    }

    @Test
    public void getExchangeRatesTest () throws Exception {
       this.mockMvc.perform(post("/currencies/exchange")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"from\":\"BTC\",\"to\":[\"USD\",\"ETH\"],\"amount\":\"121\"}"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.USD").exists())
               .andExpect(jsonPath("$.ETH").exists());
    }
}
