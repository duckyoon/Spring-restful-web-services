package com.junes.rest.webservices.restful_web_services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<User> createUser(@RequestBody User user){

        User savedUser = service.save(user);
        // Postman 에서 post 테스트 가능

        // http://localhost:8080/users/{id} 형태의 URI 생성
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // HTTP 상태코드 201 Created 와 함께 응답을 반환
        // location 헤더에 새로 생성된 리소스의 URI 가 포함됨.
        return ResponseEntity.created(location).build();
    }

}
