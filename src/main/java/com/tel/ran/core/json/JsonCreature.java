package com.tel.ran.core.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tel.ran.annotations.InjectionService;
import com.tel.ran.annotations.InjectionServiceType;
import com.tel.ran.core.Creature;
import com.tel.ran.pojo.Car;

@InjectionService(type = InjectionServiceType.JSON)
public class JsonCreature implements Creature {


    @Override
    public void create(String str) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(str, Car.class);
    }


}
