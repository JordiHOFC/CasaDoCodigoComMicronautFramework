package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
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
        return HttpResponse.ok(AutorResponse(autor))
    }

    @Get
    @Transactional
    fun listarAutores(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isBlank()){
            var autores = repository.findAll()
            return ok(autores.map {autor -> DetalhesAutorResponse(autor)})
        }
        val possivelAutor = repository.findByEmail(email)
        if (possivelAutor.isPresent){
            var autor = possivelAutor.get()
            return ok(DetalhesAutorResponse(autor))
        }
        return notFound()
    }

    @Delete(value = "/{id}")
    fun deletarAutor(@PathVariable id:Long):HttpResponse<Any>{
        var possivel = repository.findById(id)
        if (possivel.isEmpty){
            return notFound()
        }
        repository.delete(possivel.get())
        return noContent()
    }
}