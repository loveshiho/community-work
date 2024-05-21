package com.akai.encoder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestEncoder {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void testGeneratePassword() {
        String p1 = passwordEncoder.encode("1234");
        String p2 = passwordEncoder.encode("5678");
        System.out.println(p1);
        System.out.println(p2);
    }
}
