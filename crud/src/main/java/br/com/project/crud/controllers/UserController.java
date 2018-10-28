package br.com.project.crud.controllers;

import br.com.project.crud.models.User;
import br.com.project.crud.services.UserService;
import br.com.project.crud.utils.ReturnObjUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<ReturnObjUser> login(//
                        @RequestParam String username, //
                        @RequestParam String password) {
        String jwt = userService.signin(username, password);
        ReturnObjUser obj = new ReturnObjUser("Ok","welcome",jwt);
        return ResponseEntity.ok(obj);
    }

    @PostMapping("/signup")
    public ResponseEntity<ReturnObjUser> signup(@RequestBody User user) {
        String jwt = userService.signup(user);
        ReturnObjUser obj = new ReturnObjUser("Created","User Created",jwt);
        return ResponseEntity.status(201).body(obj);
    }
}