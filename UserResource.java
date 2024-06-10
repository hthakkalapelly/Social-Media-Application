package com.in28minutes.rest.webservices.restful_web_services;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
//import javax.validation.*;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.hateoas.EntitiyModel;
@RestController
public class UserResource {
    private UserDaoService service;
    public UserResource(UserDaoService service)
    {
        this.service=service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers()
    {
       return service.findAll();
    }
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id)
    {
        User user = service.findOne(id);
        if(user==null)
        {
            throw new UserNotFoundException("id :"+id);
        }
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
         service.deleteById(id);


    }
    @PostMapping("/users")
    public ResponseEntity<User> CreateUser(@RequestBody User user)
    {
     User savedUser = service.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
