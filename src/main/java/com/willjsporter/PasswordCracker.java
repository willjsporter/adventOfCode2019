package com.willjsporter;

public class PasswordCracker {


    public static boolean checkPassword(Integer passwordToCheck) {
        if (passwordIsSixDigits(passwordToCheck)) {
            int[] passwordArray = passwordToArray(passwordToCheck);
            if (checkPasswordHasRepeatedCharacter(passwordArray)) {
                return checkPasswordAscending(passwordArray);
            }
        }
        return false;
    }

    private static boolean passwordIsSixDigits(Integer passwordToCheck) {
        return passwordToCheck >= 100000 && passwordToCheck <= 999999;
    }

    private static boolean checkPasswordHasRepeatedCharacter(int[] passwordArray) {
        for(int i = 0; i < passwordArray.length - 1; i ++) {
            if(arrayIndexIsEqualToFollowingIndexButNotIndicesEitherSide(passwordArray, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean arrayIndexIsEqualToFollowingIndexButNotIndicesEitherSide(int[] passwordArray, int i) {
        if(i == 0) {
            return passwordArray[i] == passwordArray[i + 1] && passwordArray[i + 1] != passwordArray[i + 2];
        }
        else if(i == passwordArray.length - 2) {
            return passwordArray[i] == passwordArray[i + 1] && passwordArray[i - 1] != passwordArray[i];
        }
        else {
            return passwordArray[i] == passwordArray[i + 1]
                && passwordArray[i - 1] != passwordArray[i]
                && passwordArray[i + 1] != passwordArray[i + 2];
        }
    }

    private static boolean checkPasswordAscending(int[] passwordArray) {
        for(int i = 1; i < passwordArray.length; i ++) {
            if(passwordArray[i - 1] > passwordArray[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] passwordToArray(Integer passwordToCheck) {
        return String.valueOf(passwordToCheck).chars().map(num -> num - '0').toArray();
    }
}
