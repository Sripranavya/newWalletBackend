package com.demo;

import com.demo.Wallet.CollectionWalletRepository;
import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletService;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WalletServiceMockTest
{
    // autowiring walletService
    @Autowired
    private WalletService walletService;

    // autowiring walletRepository
    @MockBean
    private CollectionWalletRepository collectionWalletRepository;

    @Test
    public void testServiceWithOutActualRepository() throws WalletException
    {
        given(this.collectionWalletRepository.getWalletById(100))
                .willReturn(new WalletDto(1,"Pranavya", 30000.0));
        assertEquals("Pranavya",walletService.getWalletById(1).getWalletName());
    }

    @Test
    public void testGetWalletThrowsExceptionTest() throws WalletException
    {

        given(this.collectionWalletRepository.getWalletById(2))
                .willReturn(null);
        assertThrows(WalletException.class,()->walletService.getWalletById(2));
    }

    @Test
    public void addFundsToWalletTest() throws WalletException
    {
        // adding funds to wallet
        given(this.collectionWalletRepository.getWalletById(2))
                .willReturn(new WalletDto(2,"Navya",25000.0));
        given(this.collectionWalletRepository.getWalletById(4))
                .willReturn(new WalletDto(4,"Sri",20000.0));

        Double newBalance=this.walletService.addFundsToWalletById(2,5000.0);
        assertEquals(30000.0,newBalance);
    }

    @Test
    public void withdrawFundsFromWalletTest() throws WalletException
    {
        // withdraw funds from wallet
        given(this.collectionWalletRepository.getWalletById(2))
                .willReturn(new WalletDto(2,"Navya",25000.0));
        given(this.collectionWalletRepository.getWalletById(4))
                .willReturn(new WalletDto(4,"Sri",20000.0));

        Double newBalance=this.walletService.withdrawFundsFromWalletById(2,5000.0);
        assertEquals(20000.0,newBalance);
    }
    @Test
    public void withdrawFundsFromWalletInsufficientFundExceptionTest() throws WalletException
    {
        // insufficient balance exception
        given(this.collectionWalletRepository.getWalletById(2))
                .willReturn(new WalletDto(2,"Navya",25000.0));
        given(this.collectionWalletRepository.getWalletById(4))
                .willReturn(new WalletDto(4,"Sri",20000.0));


        assertThrows(WalletException.class,()->this.walletService.withdrawFundsFromWalletById(2,30000.0));
    }
    @Test
    public void withdrawFundsFromWalletInsufficientFundExceptionMessageTest() throws WalletException
    {
        // withdrawing insufficient funds throws exception
        given(this.collectionWalletRepository.getWalletById(2))
                .willReturn(new WalletDto(2,"Navya",20000.0));
        given(this.collectionWalletRepository.getWalletById(4))
                .willReturn(new WalletDto(4,"Sri",20000.0));
        String eMessage="";
        try{
            this.walletService.withdrawFundsFromWalletById(2,40000.0);
        }
        catch (WalletException e){
            eMessage=e.getMessage();
        }
        assertEquals("Insufficient balance, current balance:20000.0",eMessage);
    }

}