package com.junes.rest.webservices.restful_web_services.user;

import com.junes.rest.webservices.restful_web_services.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserDaoService service;

    private UserRepository repository;


    public UserJpaResource(UserDaoService service, UserRepository repository){

        this.service = service;
        this.repository = repository;
    }

    //GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUser(){
        return getAll();
    }

    private List<User> getAll() {
        return repository.findAll();
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
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    //POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser = repository.save(user);
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
    @DeleteMapping ("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping ("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        return user.get().getPosts();
    }



}
