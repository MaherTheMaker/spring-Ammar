package com.javainuse.controller;

import com.javainuse.dao.ParameterRepo;
import com.javainuse.model.Estate;
import com.javainuse.model.MyParameter;
import com.javainuse.service.MyParametersService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController()

public class ParametersController {
    @Autowired
    ParameterRepo repo;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private MyParametersService myParametersService;

    @RequestMapping(value = "Param/get", method = RequestMethod.GET)
    ResponseEntity<?> getParamByKey(@RequestParam("key") String key) {

        return ResponseEntity.ok(myParametersService.getParamByKey(key));



    }

    @RequestMapping(value = "Param/getAll", method = RequestMethod.GET)
    ResponseEntity<?> getAllParams() {

        return ResponseEntity.ok(myParametersService.getAllParams());


    }

    @RequestMapping(value = "Param/edit/{key}", method = RequestMethod.PUT)
    public ResponseEntity<?> EditParam(@RequestBody MyParameter parameter, @PathVariable String key) throws Exception {
        System.out.println(key);
        MyParameter newParameter= myParametersService.EditParam(parameter,key);
        if(newParameter==null) {
            JSONObject jo = new JSONObject();
            jo.put("Message", "Key " + key + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
        }
        return ResponseEntity.ok(newParameter);
    }




    // clear all cache using cache manager
    @RequestMapping(value = "clearCache")
    public void clearCache(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();
        }
    }

    }

