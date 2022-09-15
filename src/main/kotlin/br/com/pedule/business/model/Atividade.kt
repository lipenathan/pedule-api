package br.com.pedule.business.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name="TB_ATIVIDADE")
data class Atividade(
    @Id
    @Column(name = "atividade_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "titulo")
    var titulo: String,
    @Column(name = "descricao")
    var descricao: String,
    @Column(name = "data_hora_entrega")
    var dataHorarioEntrega: LocalDateTime,
    @Column(name = "prioridade")
    var prioridade: Boolean,
    @Column(name = "categoria")
    var categoria: String,
    @Column(name = "entregue")
    var entregue : Boolean,
    @ManyToOne
    @JoinColumn(name = "materia_id")
    var materia : Materia,
    @ManyToOne
    @JoinColumn(name = "curso_id")
    var curso : Curso,
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario : Usuario
)