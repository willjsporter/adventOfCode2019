package com.willjsporter;

public class PasswordCracker {


    public boolean checkPassword(Integer passwordToCheck) {
        if (passwordIsSixDigits(passwordToCheck)) {
            int[] passwordArray = passwordToArray(passwordToCheck);
            if (checkPasswordHasRepeatedCharacter(passwordArray)) {
                return checkPasswordAscending(passwordArray);
            }
        }
        return false;
    }

    private boolean passwordIsSixDigits(Integer passwordToCheck) {
        return passwordToCheck >= 100000 && passwordToCheck <= 999999;
    }

    private boolean checkPasswordHasRepeatedCharacter(int[] passwordArray) {
        for(int i = 1; i < passwordArray.length; i ++) {
            if(passwordArray[i - 1] == passwordArray[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPasswordAscending(int[] passwordArray) {
        for(int i = 1; i < passwordArray.length; i ++) {
            if(passwordArray[i - 1] > passwordArray[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] passwordToArray(Integer passwordToCheck) {
        return String.valueOf(passwordToCheck).chars().map(num -> num - '0').toArray();
    }
}
