package com.demo.Wallet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletJpaRepository extends JpaRepository<WalletDto, Integer>
{
    WalletDto createWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer  walletId);
    WalletDto updateWallet(WalletDto wallet);
    WalletDto deleteWalletById(Integer walletId);
    List<WalletDto> findByWalletName(String name);
    @Query("SELECT wallet FROM WalletDto wallet")
    List<WalletDto> getAllWallets();

}

