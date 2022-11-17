package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name="TB_ATIVIDADE")
data class Atividade(
    @Id
    @Column(name = "atividade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "titulo")
    var titulo: String,
    @Column(name = "descricao")
    var descricao: String = "",
    @Column(name = "data_hora_entrega")
    var dataHorarioEntrega: LocalDateTime? = null,
    @Column(name = "prioridade")
    var prioridade: Boolean = false,
    @Column(name = "categoria")
    var categoria: String = "",
    @Column(name = "entregue")
    var entregue : Boolean = false,
    @ManyToOne
    @JoinColumn(name = "materia_atividade_id")
    var materia : Materia? = null,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "curso_atividade_id")
    var curso : Curso? = null,
    @ManyToOne
    @JoinColumn(name = "usuario_atividade_id")
    var usuario : Usuario
) {
    fun validar() {
        if (titulo.isEmpty()) throw NegocioException("Título da atividade é orbigatório")
        if (dataHorarioEntrega == null) throw NegocioException("Data e horário da entrega são obrigatórios")
    }
}