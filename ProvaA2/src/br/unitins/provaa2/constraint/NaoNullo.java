package br.unitins.provaa2.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NaoNullo implements ConstraintValidator<NaoNulo,Object >{

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value == null) {
			return false;
		}
		return true;
	}

}
