package com.diary.diary.config;

import com.auth0.jwt.algorithms.Algorithm;

public class JWTConfig {
    public static final String SECRET_KEY = "wqqjfjfnwfhcqewuhfewfhwpfhoqwjfqwjfwdjhfqewojfqpeojfqwejfqewfh0oqeihfiqehfcjvsdjfdsjnkfdgsjknfdsfewroiwfetp" +
            "ewdsoifewjhoifewgjpfewfjewprfewewfjopifewjpoiewfgtjpfewjpofewgajpofewajpofewap[ojkfewpoklfdslkcxvjlmkc" +
            "ewfpoewfewop-jfsdjfdsfdsfwsedfqwfqewfqewfeqwfewfqewfqewfeasxzcvmbnvcmbd";
    public static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
}
