package br.edu.iff.projetoSGCI.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DataChamadoValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataChamadoValidation {
    String message() default "Chamado inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
