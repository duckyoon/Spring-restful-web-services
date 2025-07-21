package com.junes.rest.webservices.restful_web_services.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUser(){
        return service.findAll();
    }

    //GET /users
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        return service.findOne(id);
    }

    //POST /users
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        // Postman 에서 post 테스트 가능
        service.save(user);
    }

}
