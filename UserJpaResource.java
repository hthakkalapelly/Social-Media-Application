package com.in28minutes.rest.webservices.restful_web_services;
import java.net.URI;
import java.util.List;
import java.util.Optional;
//import javax.validation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.hateoas.EntitiyModel;
@RestController
public class UserJpaResource {
    private UserDaoService service;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public UserJpaResource(UserDaoService service,UserRepository userRepository,PostRepository postRepository)
    {
        this.service=service;
        this.userRepository =userRepository;
        this.postRepository=postRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("id :"+id);
        }
        return user.get();
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }
    /*@DeleteMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id)
    {
        repository.deleteById(id);
    }*/
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id : "+id);
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post)
    {
        Optional<User> user= userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id :"+id);
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> CreateUser(@Valid @RequestBody User user)
    {
        User savedUser = userRepository.save(user);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
