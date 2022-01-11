package com.recruitment.crypto;

import com.recruitment.crypto.controllers.CryptoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CryptoApplicationTests {

    @Autowired
    private CryptoController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
