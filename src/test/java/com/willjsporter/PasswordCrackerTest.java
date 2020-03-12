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
        assertFalse(passwordCracker.checkPassword(12344));
        assertTrue(passwordCracker.checkPassword(123455));
        assertFalse(passwordCracker.checkPassword(1234566));
    }

    @Test
    public void atLeastTwoAdjacentDigitsShouldBeTheSame() {
        assertTrue(passwordCracker.checkPassword(112345));
        assertTrue(passwordCracker.checkPassword(111224));
        assertFalse(passwordCracker.checkPassword(123456));
    }
}
