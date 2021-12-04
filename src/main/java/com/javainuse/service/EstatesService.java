package com.javainuse.service;

import com.javainuse.dao.EstateRepo;
import com.javainuse.model.Estate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Service
public class EstatesService {


    @Autowired
    EstateRepo repo;


    public Estate saveEstate( Estate estate)  {
        return repo.save(estate);
    }


    public Estate updateEstate( Estate estate, Integer id) {
        Optional<Estate> byId = repo.findById(id);
        if (byId.isPresent()) {
            byId.get().setEstateName(estate.getEstateName());
            byId.get().setOriginalPrice(estate.getOriginalPrice());
            byId.get().setShares(estate.getShares());
            repo.save(byId.get());
            return (byId.get());
        }
        else
        {
               return null;
        }
    }


    public boolean isDeleteEstate( Integer id)  {
        Optional<Estate> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return true;
        }
        return false;
    }


    public Estate buyEstate( Estate estate,Integer id)  {
        Optional<Estate> byId = repo.findById(id);

        if (byId.isPresent()) {

            if(byId.get().getSold())
                return null;

            byId.get().Buy(estate.getSalePrice(), estate.getDOS(), estate.getBuyerName());
            repo.save(byId.get());
            return byId.get();
        }

        return null;
    }


    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok( repo.findAllByIsSold(false));


    }
}
