package br.com.zup.autor

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.*
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores/{id}")
class AtualizaAutorController(val repository: AutorRepository) {

    @Put
    @Transactional
    fun atualizaAutor(@PathVariable id: Long, @Valid @Body request: AtualizaAutorRequest): HttpResponse<Any> {
        val possivelAutor = repository.findById(id)
        if(possivelAutor.isEmpty){
            return notFound()
        }
        var autor = possivelAutor.get()
        autor.apply {
            email=request.email
            autor.descricao=request.descricao
        }
       // repository.update(autor)
        return ok(DetalhesAutorResponse(autor))

    }
    @Patch
    @Transactional
    fun atualizaDescricaoAutor(@PathVariable id:Long, descricaoRequest:String):HttpResponse<Any>{
        val possivelAutor = repository.findById(id)
        if(possivelAutor.isEmpty){
            return notFound()
        }
        var autor = possivelAutor.get()
        autor.apply { descricao=descricaoRequest }
        return ok(DetalhesAutorResponse(autor))
    }
}