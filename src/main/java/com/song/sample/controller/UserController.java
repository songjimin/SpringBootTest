package com.song.sample.controller;

import com.song.sample.exception.TestException;
import com.song.sample.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/person/create")
    public Person create(@RequestBody Person person) {
        return person;
    }

    @GetMapping("/test")
    public void test() {
        throw new TestException("Test Exception");
    }
}
