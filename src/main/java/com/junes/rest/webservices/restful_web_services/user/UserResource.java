package com.junes.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserResource {

    private UserDaoService service;

    public UserResource(UserDaoService service){
        this.service = service;
    }

    //GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUser(){
        return getAll();
    }

    private List<User> getAll() {
        return service.findAll();
    }

    //GET /users
//    @GetMapping("/users/{id}")
//    public User retrieveUser(@PathVariable Integer id){
//        User user = service.findOne(id);
//        if (user == null)
//            throw new UserNotFoundException("id:"+id);
//        return user;
//    }


//  HATEOAS 사용 (EntityModel, WebMvcLinkBuilder)
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //POST /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

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

    // delete 메서드 구현
    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteById(id);
    }

}
