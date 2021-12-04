package com.javainuse.dao;

import com.javainuse.model.MyParameter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParameterRepo extends CrudRepository<MyParameter,Integer> {


        Optional<MyParameter> findByMyKey(String key);
}
