package com.korn.lakes.controller;

import java.time.LocalDateTime;
import java.util.Random;

public class Token {
    private final String value;
    private final LocalDateTime expireTime;

//    ----------

    public Token() {
        this.value = generateRandom();
        this.expireTime = LocalDateTime.now().plusMinutes(10);
    }

//    ----------

    private String generateRandom() {
        Random random = new Random();
        return String.valueOf(random.nextInt(10_000, 100_000));
    }

    public String getValue() {
        if (isExpired()) {
            return null;
        } else {
            return this.value;
        }
    }

    private boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}



