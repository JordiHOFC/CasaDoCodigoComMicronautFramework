package br.com.zup.validator

import br.com.zup.autor.AutorRepository
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass


@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)
@Retention(RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
annotation class UniqueEmail (
    val message: String = "Email já cadastrado no sistema",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = [],
)

@Singleton
class UniqueEmailValidator(private val repository: AutorRepository):ConstraintValidator<UniqueEmail,String>{
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueEmail>,
        context: ConstraintValidatorContext
    ): Boolean {
        return repository.findByEmail(value!!).isEmpty

    }

}