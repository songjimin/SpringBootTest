package com.song.sample.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestException extends RuntimeException {

    public TestException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
