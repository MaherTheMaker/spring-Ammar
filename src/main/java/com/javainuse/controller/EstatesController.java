package com.javainuse.controller;

import com.javainuse.dao.EstateRepo;
import com.javainuse.model.Estate;
import com.javainuse.model.UserDTO;
import com.javainuse.service.EstatesService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EstatesController {

    @Autowired
    EstateRepo repo;

    @Autowired
    EstatesService estatesService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveEstate(@RequestBody Estate estate) throws Exception {
        return ResponseEntity.ok(estatesService.saveEstate(estate));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEstate(@RequestBody Estate estate, @PathVariable Integer id) throws Exception {

        Estate estate1 = estatesService.updateEstate(estate, id);
        if (estate1 == null) {
            JSONObject jo = new JSONObject();
            jo.put("Message", "Estate id not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
        }
        return ResponseEntity.ok(estate1);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEstate(@PathVariable Integer id) throws Exception {

        boolean deleteEstate = estatesService.isDeleteEstate(id);


        if (deleteEstate) {
            JSONObject jo = new JSONObject();
            jo.put("Message", " Estate " + id + " deleted");
            return ResponseEntity.status(HttpStatus.OK).body(jo);

        }

        JSONObject jo = new JSONObject();
        jo.put("Message", "Estate id not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> buyEstate(@RequestBody Estate estate, @PathVariable Integer id) throws Exception {

        Estate newEstate = estatesService.buyEstate(estate, id);
        if (newEstate!=null)
        {

            return ResponseEntity.ok(newEstate);
        }
        else {

            JSONObject jo = new JSONObject();
            jo.put("Message", "Estate id not found "+id +"or Already sold");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jo);
        }
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(repo.findAllByIsSold(false));


    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> SearchByName(@RequestParam("key")  String searchText) {
        System.out.println(searchText);
        return ResponseEntity.ok(repo.findAllByEstateNameContaining(searchText));


    }

}
