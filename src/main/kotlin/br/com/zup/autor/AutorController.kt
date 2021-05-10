package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.notFound
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated//define que este controlador receberá validações
@Controller("/autores")
class AutorController(val repository: AutorRepository) {
    @Post//delega o metodo que sera utilizado
    fun cadastrar(@Body @Valid request: AutorRequest): HttpResponse<AutorResponse> {
        var autor = request.paraAutor()
        repository.save(autor)
        return HttpResponse.ok(AutorResponse(autor.id, autor.nome))
    }

    @Get
    @Transactional
    fun listarAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()){
            var autores = repository.findAll()
            return ok(autores.map { autor -> AutorResponse(autor.id,autor.nome)})
        }
        val possivelAutor = repository.findByEmail(email)
        if (possivelAutor.isPresent){
            var autor = possivelAutor.get()
            return ok(AutorResponse(autor.id,autor.nome))
        }
        return notFound()
    }
}