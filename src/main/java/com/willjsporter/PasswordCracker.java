package com.willjsporter;

public class PasswordCracker {

    public boolean checkPassword(int passwordToCheck) {
        return (passwordToCheck >= 100000 && passwordToCheck <= 999999);
    }
}
