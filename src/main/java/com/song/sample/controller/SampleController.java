package com.song.sample.controller;

import com.song.sample.exception.TestException;
import com.song.sample.model.Person;
import com.song.sample.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/person")
    public Person person() {
        return sampleService.getPerson();
    }

    @GetMapping("/test")
    public void test() {
        throw new TestException("Test Exception");
    }


}



