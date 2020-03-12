package com.willjsporter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordCrackerTest {

    @Test
    public void shouldEnsureThatPasswordIsSixCharacters() {
        assertFalse(PasswordCracker.checkPassword(12344));
        assertTrue(PasswordCracker.checkPassword(123455));
        assertFalse(PasswordCracker.checkPassword(1234566));
    }

    @Test
    public void atLeastTwoAdjacentDigitsShouldBeTheSame() {
        assertTrue(PasswordCracker.checkPassword(112345));
        assertTrue(PasswordCracker.checkPassword(112245));
        assertFalse(PasswordCracker.checkPassword(123456));
    }

    @Test
    public void thereShouldBeADigitThatAppearsExactlyTwiceInARow() {
        assertTrue(PasswordCracker.checkPassword(112233));
        assertFalse(PasswordCracker.checkPassword(123444));
        assertTrue(PasswordCracker.checkPassword(111133));
    }

    @Test
    public void digitsInThePasswordShouldAlwaysIncreaseFromLeftToRight() {
        assertTrue(PasswordCracker.checkPassword(123345));
        assertFalse(PasswordCracker.checkPassword(12334567));
        assertFalse(PasswordCracker.checkPassword(113234));
    }
}
