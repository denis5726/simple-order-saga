package com.example.simplesaga.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class BCryptEncodedValidator implements ConstraintValidator<BCryptEncoded, String> {
    private static final Pattern PATTERN = Pattern.compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotBlank(s) && PATTERN.matcher(s).matches();
    }
}
