package br.com.zup.autor

data class DetalhesAutorResponse(
    val nome:String,
    val descricao:String,
    val email:String
){
    constructor(autor: Autor) : this(
        nome=autor.nome,
        descricao=autor.descricao,
        email=autor.email
    ) {

    }


}
