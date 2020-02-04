package br.unitins.provaa2.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER , TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MySizee.class })
public @interface MySize {
	
	int min() default 0;
	
	int max() default Integer.MAX_VALUE;
	
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
