package br.unitins.provaa2.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MySizee implements ConstraintValidator<MySize,String>{
	
	protected int min,max;
	
	@Override
	public void initialize(MySize constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
		min =constraintAnnotation.min();
		max =constraintAnnotation.max();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value == null) {
			return false;
		}
		if(value.length() >= min && value.length() <= max) {
			return true;
		}
		return false;
	}


}
