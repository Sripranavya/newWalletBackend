package com.demo.Wallet;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@Repository
public class CollectionWalletRepositoryImpl implements CollectionWalletRepository
{
    private Map<Integer, WalletDto> walletMap = new HashMap<>();

    @Override
    public WalletDto createWallet(WalletDto newWallet)
    {
        //creates a new wallet
        this.walletMap.put(newWallet.getWalletId(),newWallet);
        return newWallet;
    }

    @Override
    public WalletDto getWalletById(Integer walletId)
    {
        // returns wallet ID
        return this.walletMap.get(walletId);
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet)
    {
        // updating wallet details in map
        this.walletMap.replace(wallet.getWalletId(),wallet);
        return wallet;
    }

    @Override
    public WalletDto deleteWalletById(Integer walletId)
    {
        // removing wallet ID from map
        return  this.walletMap.remove(walletId);
    }

    @Override
    public Collection<WalletDto> getAllWallets()
    {
        //getting all wallets
        return  this.walletMap.values();
    }
}

