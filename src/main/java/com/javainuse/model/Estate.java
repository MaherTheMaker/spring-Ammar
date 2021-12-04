package com.javainuse.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Estates")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String estateName;

    double OriginalPrice;

    Double shares;



    Boolean isSold=false;

    Double SalePrice;

    Date DOS;

    String buyerName;

    public Estate(String name, double originalPrice, Double shares) {
        estateName = name;
        OriginalPrice = originalPrice;
        this.shares = shares;
    }

    public Estate() {

    }


    public void Buy(Double salePrice, Date DOS, String buyerName)
    {
        this.isSold = true;
        SalePrice = salePrice;
        this.DOS = DOS;
        this.buyerName = buyerName;
    }

    public Estate(Integer id, String name, double originalPrice, Double shares, Boolean isSold, Double salePrice, Date DOS, String buyerName) {
        this.id = id;
        estateName = name;
        OriginalPrice = originalPrice;
        this.shares = shares;
        this.isSold = isSold;
        SalePrice = salePrice;
        this.DOS = DOS;
        this.buyerName = buyerName;
    }

    public Integer getId() {
        return id;
    }


    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        OriginalPrice = originalPrice;
    }

    public Double getShares() {
        return shares;
    }

    public void setShares(Double shares) {
        this.shares = shares;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public Double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Double salePrice) {
        SalePrice = salePrice;
    }

    public Date getDOS() {
        return DOS;
    }

    public void setDOS(Date DOS) {
        this.DOS = DOS;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
}
