package com.in28minutes.rest.webservices.restful_web_services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson()
    {
        return new PersonV1("Bob charlie");
    }
   @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson()
    {
        return new PersonV2(new Name("Charlie","Bob"));
    }
    @GetMapping(path="/person",params="version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter()
    {
        return new PersonV1("Charlie Bob");
    }
    @GetMapping(path="/person",params="version=2")
    public PersonV2 getSecondVersionOfPersonRequestP()
    {
        return new PersonV2(new Name("Charlie","Bob"));
    }

    @GetMapping(path="/person/header",headers="X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader()
    {
        return new PersonV1("Charlie Bob");
    }
    @GetMapping(path="/person/header",headers="X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader()
    {
        return new PersonV2(new Name("Charlie","Bob"));
    }

}
