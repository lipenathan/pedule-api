package br.com.pedule.business.model

import java.time.LocalTime
import javax.persistence.*

@Entity(name = "TB_SEMANA_HORARIO")
data class SemanaHorario(
    @Id
    @Column(name = "semana_horario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "horario")
    var horario: LocalTime,
    @ManyToOne
    @JoinColumn(name = "semana_id")
    var semana: DiaSemana
)