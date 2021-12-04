package com.javainuse.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
public class MyParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
  @Column(unique=true)
    String myKey;

    String myValue;
   public MyParameter() {
    }

    public MyParameter(String mykey, String value) {
        this.myKey = mykey;
        this.myValue = value;
    }

    public Integer getId() {
        return id;
    }

    public String getMyvalue() {
        return myValue;
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyvalue(String value) {
        this.myValue = value;
    }
}

