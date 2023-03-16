package com.demo;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WalletServiceIntegrationTest {
    //autowiring wallet service
    @Autowired
    private WalletService walletService;
    @Test
    void registerWalletTest() throws WalletException
    {
        //creating new WalletDto
        WalletDto wall = new WalletDto(1,"Pranavya",30000.0);
        assertEquals("Pranavya",this.walletService.registerWallet(wall).getWalletName());
    }
    @Test
    void getWalletByIdThrowsExceptionTest() throws WalletException
    {
        //WalletDto wall = new WalletDto(1,"Pranavya",30000.0, xyz@gmail.com, 256);
        assertThrows(WalletException.class,()->this.walletService.getWalletById(1));
    }

    @Test
    void updateWalletTest() throws WalletException
    {
        //creating newWallDto and assigning values
        WalletDto newWallDto = new WalletDto(2,"Navya",30000.0);
        assertEquals("Navya",this.walletService.updateWallet(newWallDto).getWalletName());

    }

    @Test
    void deleteWalletByIdThrowsException() throws WalletException
    {
        //WalletDto wall= new WalletDto(1,"Pranavya",30000.0,"xyz@gmail.com",256);
        WalletDto deletewalletDto = walletService.deleteWalletById(1);
        assertThrows(WalletException.class,()->this.walletService.deleteWalletById(1));
    }
}

