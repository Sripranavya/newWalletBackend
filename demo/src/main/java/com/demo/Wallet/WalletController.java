package com.demo.Wallet;

import java.util.List;
import java.util.Collection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@ComponentScan
@CrossOrigin(value = "http://localhost:4200/")
@RequestMapping(value = "/wallet")
@RestController
@EnableJpaRepositories("com.demo.Wallet.WalletJpaRepository")

public class WalletController
{
    // autowiring walletService
    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ResponseEntity<String> welcome()
    {
        //get mapping
        return ResponseEntity.ok().body("Hii, Warm Welcome to the Wallet Application! Specify the crud operations to be performed.");
    }

    @RequestMapping(value = "newwallet", method = RequestMethod.POST )
    public WalletDto registerWallet(@RequestBody WalletDto wallet) throws WalletException
    {
        //registering a new wallet
        return this.walletService.registerWallet(wallet);
    }

    @RequestMapping(value = "wallet/{walletId}", method = RequestMethod.GET)
    public WalletDto getWalletById(@PathVariable("walletId") Integer walletId) throws WalletException
    {
        //returns wallet by Id
        return this.walletService.getWalletById(walletId);
    }
    @RequestMapping(value = "updatewallet", method = RequestMethod.PUT)
    public ResponseEntity<WalletDto> updateWallet(@RequestBody WalletDto wallet) throws WalletException
    {
        //updates wallet
        return ResponseEntity.ok().body(this.walletService.updateWallet(wallet));
    }
    @RequestMapping(value = "wallet/{walletId}", method = RequestMethod.DELETE)
    public ResponseEntity<WalletDto> deleteWallet(@PathVariable("walletId") Integer walletId) throws WalletException
    {
        // deletes a wallet by ID
        return ResponseEntity.ok().body(this.walletService.deleteWalletById(walletId));
    }

    @RequestMapping(value = "wallet/{walletId}/{amount}", method = RequestMethod.PATCH)
    public Double addFundsToWalletById(@PathVariable("walletId")Integer walletId,@PathVariable("amount") Double amount) throws WalletException
    {
        // adds funds to wallet
        return this.walletService.addFundsToWalletById(walletId,amount);
    }
    @RequestMapping(value = "wallet/{walletId}/fund/{amount}", method = RequestMethod.PATCH)
    public Double withdrawFundsfromWalletById(@PathVariable("walletId")Integer walletId,@PathVariable("amount") Double amount) throws WalletException
    {
        // withdraws funds from a wallet
        return this.walletService.withdrawFundsFromWalletById(walletId,amount);
    }

    @RequestMapping(value = "wallet/fund", method = RequestMethod.PATCH)
    public Boolean fundTransfer(@RequestBody WalletTransferDto walletTransferDto) throws WalletException
    {
        // transfers fund from one wallet to another
        return this.walletService.fundTransfer(walletTransferDto.getFromWalletId(),walletTransferDto.getToWalletId(),walletTransferDto.getAmount());
    }

    @Autowired
    WalletJpaRepository walletJpaRepository;
    @RequestMapping(value = "wallet/name/{walletName}", method = RequestMethod.GET)
    public List<WalletDto> getAllWalletsHavingName(@PathVariable("walletName") String name)
    {
        //returns wallet by name
        return this.walletJpaRepository.findByWalletName(name);
    }

    @RequestMapping(value = "allwallets", method = RequestMethod.GET)
    public Collection<WalletDto> getAllWallets()
    {
        // returns all wallets
        return this.walletService.getAllWallets();
    }
}

