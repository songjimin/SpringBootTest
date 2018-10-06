package com.song.sample.service;

import com.song.sample.model.Person;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public Person getPerson() {
        return new Person("JIMIN", 33);
    }
}
