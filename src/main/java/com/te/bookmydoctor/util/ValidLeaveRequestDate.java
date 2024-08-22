package com.te.bookmydoctor.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*@Target({ FIELD, ANNOTATION_TYPE, METHOD, PARAMETER, CONSTRUCTOR })
@Retention(RUNTIME)*/
@Constraint(validatedBy = LeaveRequestDateValidator.class)
public @interface ValidLeaveRequestDate {
    String message() default "Invalid leave request dates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}