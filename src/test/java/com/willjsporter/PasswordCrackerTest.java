package com.willjsporter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordCrackerTest {

    private PasswordCracker passwordCracker;

    @Before
    public void setup() {
        passwordCracker = new PasswordCracker();
    }

    @Test
    public void shouldEnsureThatPasswordIsSixCharacters() {
        assertFalse(passwordCracker.checkPassword(12345));
        assertTrue(passwordCracker.checkPassword(123456));
        assertFalse(passwordCracker.checkPassword(1234567));
    }
}
