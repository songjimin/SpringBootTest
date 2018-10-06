package com.song.sample.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidUtil {

    public static String createUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
