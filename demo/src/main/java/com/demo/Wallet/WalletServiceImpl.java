package com.demo.Wallet;

import java.util.Collection;

import com.demo.Wallet.WalletDto;
import com.demo.Wallet.WalletException;
import com.demo.Wallet.WalletJpaRepository;
import com.demo.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class WalletServiceImpl implements WalletService
{
    // autowiring walletJpaRepository
    @Autowired
    private WalletJpaRepository walletJpaRepository;

    @Override
    public WalletDto registerWallet(WalletDto wallet) throws WalletException
    {
        //registering new wallet
        return this.walletJpaRepository.createWallet(wallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException
    {
        // custom exceptions for invalid wallet ID
        WalletDto wallet = this.walletJpaRepository.getWalletById(walletId);
        if(wallet==null)
            throw new WalletException("Wallet ID is invalid or has been deleted !"+walletId);
        return wallet;
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException
    {
        // update wallet
        if(wallet== null)
            throw new WalletException("Cannot update empty wallet ! Try again later");
        return this.walletJpaRepository.updateWallet(wallet);
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException
    {
        // delete wallet by ID
        if(walletId == null)
            throw new WalletException("Wallet ID does not exist here!!" +walletId);
        return this.walletJpaRepository.deleteWalletById(walletId);
    }


    @Override
    public Double addFundsToWalletById(Integer walletId, Double amount) throws WalletException
    {
        // adding funds from one wallet to another wallet
        WalletDto wallet = this.walletJpaRepository.getWalletById(walletId);
        if(wallet==null)
            throw new WalletException("Wallet ID specified is invalid to add money, WalletId:"+walletId);

        Double newBalance = wallet.getWalletBalance()+amount;
        wallet.setWalletBalance(newBalance);
        this.walletJpaRepository.updateWallet(wallet);
        return newBalance;
    }

    @Override
    public Double withdrawFundsFromWalletById(Integer walletById, Double amount) throws WalletException
    {
        // withdrawing funds from wallet
        WalletDto wallet = this.walletJpaRepository.getWalletById(walletById);
        if(wallet==null)
            throw new WalletException("Wallet ID is invalid to withdraw, make use of a  valid wallet ID");
        Double balance= wallet.getWalletBalance();
        if(balance<amount)
            throw new WalletException("Insufficient balance, current balance:"+balance);
        balance-=amount;
        wallet.setWalletBalance(balance);
        this.walletJpaRepository.updateWallet(wallet);
        return balance;
    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException
    {
        // transfering funds from one Wallet ID to another Wallet ID
        WalletDto fromWallet = this.walletJpaRepository.getWalletById(fromWalletId);

        if(fromWallet == null)
            throw new WalletException("From Wallet ID is invalid.Please enter a valid one!, id:"+fromWalletId);
        WalletDto toWallet = this.walletJpaRepository.getWalletById(toWalletId);

        if(toWallet== null)
            throw new WalletException("To Wallet ID is invalid.Please enter a valid one! id:"+toWalletId);
        Double fromBalance = fromWallet.getWalletBalance();

        if(fromBalance<amount)
            throw new WalletException("Insufficient balance available, current balance:"+fromBalance);

        fromWallet.setWalletBalance(fromBalance-amount);
        Double toBalance = toWallet.getWalletBalance();
        toWallet.setWalletBalance(toBalance+amount);
        this.walletJpaRepository.updateWallet(fromWallet);
        this.walletJpaRepository.updateWallet(toWallet);

        return true;
    }

    @Override
    public Collection<WalletDto> getAllWallets()
    {
        // returning all wallets
        return this.walletJpaRepository.getAllWallets();
    }
}
