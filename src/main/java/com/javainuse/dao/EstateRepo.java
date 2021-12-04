package com.javainuse.dao;

import com.javainuse.model.Estate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstateRepo extends CrudRepository<Estate,Integer>
{

    List<Estate> findAllByIsSold(Boolean isSold);
    List<Estate> findAllByBuyerName(String BuyerName);
    List<Estate> findAllByEstateName(String EstateName);
    List<Estate> findAllByEstateNameContaining(String EstateName);
//    List<Estate> findAllByEstateName(String estateName);
}
