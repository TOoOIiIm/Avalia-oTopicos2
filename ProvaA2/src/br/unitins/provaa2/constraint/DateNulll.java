package br.unitins.provaa2.constraint;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateNulll implements ConstraintValidator<DateNull, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value == null) {
			return false;
		}
		return true;
	}

}
