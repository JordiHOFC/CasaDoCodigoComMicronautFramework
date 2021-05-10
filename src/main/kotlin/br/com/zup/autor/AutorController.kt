package br.com.zup.autor

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated//define que este controlador receberá validações
@Controller("/autores")
class AutorController(val repository: AutorRepository) {

    @Post//delega o metodo que sera utilizado
    fun cadastrar(@Body @Valid request: AutorRequest): AutorResponse{
        var autor = request.paraAutor()
        repository.save(autor)
        return AutorResponse(autor.id,autor.nome)
    }
}