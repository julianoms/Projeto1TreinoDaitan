package br.com.crud.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateController {
    

    @RequestMapping("/home")
    public String create(@RequestParam(value="name",defaultValue = "Juliano") String name){
        System.out.println("create");
        return name;
    }


}
