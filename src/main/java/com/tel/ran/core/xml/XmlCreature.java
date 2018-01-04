package com.tel.ran.core.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tel.ran.annotations.InjectionService;
import com.tel.ran.annotations.InjectionServiceType;
import com.tel.ran.core.Creature;
import com.tel.ran.pojo.Car;

@InjectionService(type = InjectionServiceType.XML)
public class XmlCreature implements Creature{
    @Override
    public void create(String str) throws Exception {
        XmlMapper mapper = new XmlMapper();
        Car car = mapper.readValue(str, Car.class);
    }
}
