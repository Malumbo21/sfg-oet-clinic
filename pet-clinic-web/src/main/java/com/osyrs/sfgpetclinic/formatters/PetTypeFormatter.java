package com.osyrs.sfgpetclinic.formatters;

import com.osyrs.sfgpetclinic.model.PetType;
import com.osyrs.sfgpetclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }
    @Override
    public String print(PetType petType, Locale locale){
        return petType.getName();
    }

    @Override
    public PetType parse(String text,Locale locale) throws ParseException{
        return petTypeService.findAll()
                .stream()
                .filter(type -> type.getName().equals(text))
                .findAny().orElseThrow(()->new ParseException("Type not found: "+text,0));
    }
}
