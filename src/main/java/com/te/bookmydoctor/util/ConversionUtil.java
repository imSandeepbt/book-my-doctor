package com.te.bookmydoctor.util;

import org.hibernate.query.sqm.sql.ConversionException;
import org.springframework.beans.BeanUtils;

public class ConversionUtil {

    public static <T> T convertToDto(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            throw new IllegalArgumentException("Source or targetClass cannot be null");
        }
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new ConversionException("Error converting to DTO", e);
        }
        return target;
    }

    public static <T> T convertToEntity(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            throw new IllegalArgumentException("Source or targetClass cannot be null");
        }
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            throw new ConversionException("Error converting to Entity", e);
        }
        return target;
    }
}
