package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import java.time.Duration
import javax.persistence.*

@Entity(name="TB_CURSO")
data class Curso(
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "nome")
    var nome: String = "",
    @Column(name = "descricao")
    var descricao: String = "",
    @Column(name = "cor")
    var cor: String = "",
    @Column(name = "carga_horaria")
    var cargaHoraria: Duration? = null,
    @ManyToOne
    @JoinColumn(name = "usuario_curso_id")
    var usuario : Usuario
) {
    fun validar() {
        if (this.nome.isNullOrEmpty()) throw NegocioException("Nome da curso precisa ser preenchido")
    }
}