package br.com.pedule.business.model

import javax.persistence.*

@Entity(name="TB_SEMANA")
data class DiaSemana(
    @Id
    @Column(name = "semana_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "dia")
    var nome: String = ""
)