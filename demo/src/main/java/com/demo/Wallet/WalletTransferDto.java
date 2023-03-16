package com.demo.Wallet;

public class WalletTransferDto
{
    //Transfer POJO Class
    private Integer fromWalletId; // from wallet ID
    private Integer toWalletId; // to wallet ID
    private Double amount; // wallet amount

    public WalletTransferDto()
    {
        //default constructor
    }

    public WalletTransferDto(Integer fromWalletId, Integer toWalletId, Double amount)
    {
        // parameterized constructor
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.amount = amount;
    }

    public Integer getFromWalletId()
    {
        // returns from wallet
        return fromWalletId;
    }

    public void setFromId(Integer fromWalletId)
    {
        // sets from wallet
        this.fromWalletId = fromWalletId;
    }

    public Integer getToWalletId()
    {
        // returns to wallet
        return toWalletId;
    }

    public void setToWalletId(Integer toWalletId)
    {
        // sets to wallet
        this.toWalletId = toWalletId;
    }

    public Double getAmount()
    {
        // returns wallet amount
        return amount;
    }

    public void setWalletAmount(Double amount)
    {
        // sets wallet amount
        this.amount = amount;
    }
}


