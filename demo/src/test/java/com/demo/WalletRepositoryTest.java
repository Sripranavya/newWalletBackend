package com.demo;

import com.demo.Wallet.CollectionWalletRepository;
import com.demo.Wallet.WalletDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletRepositoryTest {
    // autowiring WalletRepository
    @Autowired
    CollectionWalletRepository collectionWalletRepository;

    @BeforeEach
    public void start()
    {
        //initialise wallet
        collectionWalletRepository.createWallet(new WalletDto(1,"Pranavya",30000.0));
    }
    @Test
    public void getWalletByIdTest()
    {
        //get wallet
        WalletDto walletDto = collectionWalletRepository.getWalletById(1);
    }

    @Test
    public void updateWalletTest()
    {
        //update wallet
        WalletDto newDto= collectionWalletRepository.createWallet(new WalletDto(2,"Navya",25000.0));
        WalletDto walletDto = collectionWalletRepository.updateWallet(newDto);
    }

    @Test
    public void deleteWalletTest()
    {
        //delete wallet
        WalletDto deletedWallet = collectionWalletRepository.deleteWalletById(1);
        assertEquals("Pranavya",deletedWallet.getWalletName());
    }
}

