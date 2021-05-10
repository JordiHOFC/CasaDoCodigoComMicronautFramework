package br.com.zup.autor

data class AutorResponse(val id:Long?, val nome:String){

    constructor(autor:Autor) : this( id=autor.id, nome=autor.nome) {

    }

}
