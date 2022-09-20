package com.osyrs.sfgpetclinic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = {"","/","/index","/index.html"})
public @interface Index {
   /* @AliasFor(
            annotation = RequestMapping.class
    )
    String[] value() default {"","/","/index","/index.html"};*/
}
