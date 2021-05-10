package br.com.zup.autor

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    var descricao: String,
    var email: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null
    @CreationTimestamp
    var criadoEm=LocalDateTime.now()
}