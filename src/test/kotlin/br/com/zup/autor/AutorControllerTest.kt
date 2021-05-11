package br.com.zup.autor

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import javax.inject.Inject

@MicronautTest
internal class AutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {
        autor = Autor(
            nome = "Alberto Tavares Souza",
            descricao = "criador deo cognitive developer designer",
            email = "alberto.souza@zup.com.br"
        )
        autorRepository.save(autor)
    }

    @AfterEach
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve retornar um autor quando email valido informado`() {
        val response = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutorResponse::class.java)
        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(
            autor.email, response.body()!!.email
        )

    }
    @Test
    internal fun `nao deve retornar um autor quando email valido informado`() {
        val response = client.toBlocking().retrieve("/autores?email=jordi@exp.com")
        assertNull(response)
    }

}