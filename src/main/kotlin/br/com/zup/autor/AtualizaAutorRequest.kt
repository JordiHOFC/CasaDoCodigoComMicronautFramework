package br.com.zup.autor

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Introspected
class AtualizaAutorRequest(@field:NotBlank val descricao:String, @field:NotBlank @field:Email val email:String) {

}