package com.demo.Wallet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WalletDto
{
    //POJO Class

    @Id
    @GeneratedValue
    private Integer WalletId; // wallet ID
    private String WalletName; // wallet Name
    private Double WalletBalance; // wallet Balance


    public WalletDto()
    {
        //default constructor
    }

    public WalletDto(Integer WalletId, String WalletName, Double WalletBalance)
    {
        //parameterized constructor
        this.WalletId = WalletId;
        this.WalletName = WalletName;
        this.WalletBalance = WalletBalance;
    }

    public Integer getWalletId()
    {
        // returns wallet ID
        return WalletId;
    }

    public void setWalletId(Integer WalletId)
    {
        // sets wallet ID
        this.WalletId = WalletId;
    }

    public String getWalletName()
    {
        // returns wallet name
        return WalletName;
    }

    public void setWalletName(String WalletName)
    {
        // sets wallet name
        this.WalletName = WalletName;
    }

    public Double getWalletBalance()
    {
        // returns wallet balance
        return WalletBalance;
    }

    public void setWalletBalance(Double WalletBalance)
    {
        // sets wallet balance
        this.WalletBalance = WalletBalance;
    }

    @Override
    public String toString() {
        return "Wallet {" +
                "WalletID = " + WalletId +
                ", WalletName ='" + WalletName + '\'' +
                ", WalletBalance =" + WalletBalance +
                '}';
    }
}



