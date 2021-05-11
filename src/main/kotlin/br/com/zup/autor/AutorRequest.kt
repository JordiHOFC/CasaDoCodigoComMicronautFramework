package br.com.zup.autor

import br.com.zup.validator.UniqueEmail
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import kotlin.math.max

@Introspected //tornao um bean, permitindo o acesso em tempo de compilação
data class AutorRequest(
    @field:NotBlank val nome: String,
    @field:Size(max=400) @field:NotBlank val descricao: String,
    @field:UniqueEmail @field:Email @field:NotBlank val email: String
) {
    fun paraAutor(): Autor {
        return Autor(nome=nome,descricao=descricao,email=email)
    }


}
