package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IpRangeValidator implements ConstraintValidator<ValidIpRange, String> {

    private static final String IP_ADDRESS_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final String IP_RANGE_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)/(3[0-2]|[1-2][0-9]|[0-9])$";

    private static final Pattern ipAddressPattern = Pattern.compile(IP_ADDRESS_PATTERN);
    private static final Pattern ipRangePattern = Pattern.compile(IP_RANGE_PATTERN);

    @Override
    public void initialize(ValidIpRange constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ipAddressPattern.matcher(value).matches() || ipRangePattern.matcher(value).matches();
    }
}
