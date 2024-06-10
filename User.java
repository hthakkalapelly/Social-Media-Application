package com.in28minutes.rest.webservices.restful_web_services;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_details")
public class User {
    protected User()
    {

    }
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts ;
    public User(Integer id,String name,LocalDate birthDate)
    {
        super();
        this.id=id;
        this.name=name;
        this.birthDate=birthDate;
    }
    public Integer getId()
    {

        return id;
    }
    public void setId(Integer id)
    {
        this.id=id;
    }
    public String getName()
    {

        return name;
    }
    public LocalDate getBirthDate()
    {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate=birthDate;
    }
    public List<Post> getPosts()
    {
        return posts;
    }
    public void setPosts(List<Post> posts)
    {
        this.posts=posts;
    }
    @Override
    public String toString()
    {

        return "User [id=" + id + ", name=" + name + ",birthDate="+ birthDate +"]";
    }
}
