package com.skillBridge.user.utils;

import java.util.regex.Pattern;

public final class StringValidationUtil {

    private StringValidationUtil() {}

    // ===== REGEX PATTERNS =====

    // Name → Alphabets + space
    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[A-Za-z ]+$");

    // Phone → Numbers only (7 to 15 digits safe global range)
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9]{7,15}$");

    // Address → Alphanumeric + comma + space
    private static final Pattern ADDRESS_PATTERN =
            Pattern.compile("^[A-Za-z0-9, ]+$");

    // Email → Standard Email Validation
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    //AADHAR → Numeric and 12 digit long
    private static final Pattern AADHAR_PATTERN =
            Pattern.compile("^[0-9]{12}$");


    // ===== VALIDATION METHODS =====

    /**
     * Validate Name → Only alphabets and space
     */
    public static boolean isValidName(String name) {
        return name != null &&
                !name.trim().isEmpty() &&
                NAME_PATTERN.matcher(name).matches();
    }

    /**
     * Validate Phone → Only digits
     */
    public static boolean isValidPhone(String phone) {
        return phone != null &&
                PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * Validate Address → Alphanumeric + comma + space
     */
    public static boolean isValidAddress(String address) {
        return address != null &&
                !address.trim().isEmpty() &&
                ADDRESS_PATTERN.matcher(address).matches();
    }

    /**
     * Validate Email → Standard email format
     */
    public static boolean isValidEmail(String email) {
        return email != null &&
                !email.trim().isEmpty() &&
                EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validate Aadhar
     */
    public static boolean isValidAADHARNumber(String aadharNumber) {
        return aadharNumber != null &&
                !aadharNumber.trim().isEmpty() && AADHAR_PATTERN.matcher(aadharNumber).matches();
    }

    /**
     * Valid String
     */

    // Email → Standard Email Validation
    private static final Pattern STRING_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]");
    public static boolean isValidString(String string) {
        return string != null && !string.trim().isEmpty() && STRING_PATTERN.matcher(string).matches();
    }
}

