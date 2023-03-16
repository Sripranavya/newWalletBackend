package com.demo;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WalletControllerTest
{
    @Value(value="${local.server.port}")
    private int port;

    // autowiring rest template
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void start()
    {
        // create new wallet
        this.restTemplate.postForObject("http://localhost:" + port + "/wallet",new WalletDto(1,"Pranavya",25000.0),WalletDto.class);
    }
    @Test
    public void getWalletByIdTest() throws WalletException
    {
        // get wallet By ID
        WalletDto foundWallet =this.restTemplate.getForObject("http://localhost:" + port + "/wallet/1", WalletDto.class);
        assertEquals("Pranavya",foundWallet.getWalletName());
    }

    @Test
    public void getWalletByIdExceptionTest() throws WalletException
    {
        // wallet Exception
        String walletExceptionMessage =this.restTemplate.getForObject("http://localhost:" + port + "/wallet/1", String.class);
        assertEquals("Wallet Id does not exists.",walletExceptionMessage);
    }
}

