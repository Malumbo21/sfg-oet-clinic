package com.osyrs.sfgpetclinic.controllers;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"","/","/index","/index.html"})
public @interface Index {
    @AliasFor(
            annotation = RequestMapping.class
    )
    String[] value() default {"","/","/index","/index.html"};
}
