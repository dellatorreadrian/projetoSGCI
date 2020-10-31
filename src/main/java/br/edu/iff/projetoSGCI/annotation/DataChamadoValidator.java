package br.edu.iff.projetoSGCI.annotation;

import br.edu.iff.projetoSGCI.model.Chamado;
import br.edu.iff.projetoSGCI.model.StatusChamadoEnum;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DataChamadoValidator implements ConstraintValidator<DataChamadoValidation, Chamado>{

    @Override
    public boolean isValid(Chamado value, ConstraintValidatorContext cvc) {
        if (value.getDataAbertura() != null){
            if (value.getDataEncerramento() != null) {
                if ((value.getStatus() == StatusChamadoEnum.CANCELADO) || (value.getStatus() == StatusChamadoEnum.ENCERRADO)){
                    return (value.getDataAbertura().before(value.getDataEncerramento()));
                }
                return false;
            }
            return ((value.getStatus() != StatusChamadoEnum.CANCELADO) && (value.getStatus() != StatusChamadoEnum.ENCERRADO));
        }
        return false;
    }
}