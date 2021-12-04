package com.javainuse.service;

import com.javainuse.dao.ParameterRepo;
import com.javainuse.model.MyParameter;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class MyParametersService {
    @Autowired
    ParameterRepo repo;


    public MyParameter getParamByKey(String key) {

        return repo.findByMyKey(key).get();


    }

    @Cacheable(value = "parameters")
    public List<MyParameter> getAllParams() {

        System.out.println("No cached has been used");
        return (List<MyParameter>) repo.findAll();


    }

    @CacheEvict(value = "parameters", allEntries = true)
    public MyParameter EditParam(MyParameter parameter, String key) {
        Optional<MyParameter> byId = repo.findByMyKey(key);
        if (byId.isPresent()) {
            byId.get().setMyvalue(parameter.getMyvalue());
            repo.save(byId.get());
            return byId.get();
        }

        return null;
    }

}
