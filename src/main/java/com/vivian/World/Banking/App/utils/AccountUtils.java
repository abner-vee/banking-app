package com.vivian.World.Banking.App.utils;

import java.time.Year;

public class AccountUtils {

    public static String ACCOUNT_EXISTS_CODE = "001";

    public static String ACCOUNT_EXISTS_MESSAGE = "This user already has an account";

    public static String ACCOUNT_NUMBER_NOT_EXIST_CODE = "003";

    public static String ACCOUNT_NUMBER_NOT_FOUND_MESSAGE = "Provided Account Number Does Not Exist";

    public static String ACCOUNT_CREDITED_SUCCESS_CODE = "004";

    public static String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "Account credited successfully";

    public static String INSUFFICIENT_BALANCE_CODE = "005";

    public static String INSUFFICIENT_BALANCE_MESSAGE = "Account balance insufficient";

    public static String ACCOUNT_DEBITED_SUCCESS_CODE = "006";

    public static String ACCOUNT_DEBITED_SUCCESS_MESSAGE = "SUCCESS!";

    public static String ACCOUNT_NUMBER_FOUND_CODE = "007";

    public static String ACCOUNT_NUMBER_FOUND_MESSAGE = "Account number found";

    public static String TRANSFER_SUCCESSFUL_CODE = "008";

    public static String TRANSFER_SUCCESSFUL_MESSAGE = "Transfer successful";






    public static String generateAccountNUmber(){
        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;


        int randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        String year = String.valueOf(currentYear);
        String randomNUm = String.valueOf(randomNumber);

        StringBuilder accountNumber = new StringBuilder();
        return accountNumber.append(year).append(randomNUm).toString();
    }
}
