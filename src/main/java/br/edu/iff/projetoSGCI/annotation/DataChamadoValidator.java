package br.edu.iff.projetoSGCI.annotation;

import br.edu.iff.projetoSGCI.model.Chamado;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataChamadoValidator implements ConstraintValidator<DataChamadoValidation, Chamado>{

    @Override
    public boolean isValid(Chamado value, ConstraintValidatorContext cvc) {
        if (value.getDataAbertura() != null){
            if (value.getDataEncerramento() == null) {
                return true;
            }
            return (value.getDataAbertura().before(value.getDataEncerramento()));
        }
        return false;
    }
    
}
