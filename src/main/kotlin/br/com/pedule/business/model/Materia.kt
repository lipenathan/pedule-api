package br.com.pedule.business.model

import javax.persistence.*

@Entity(name="TB_MATERIA")
data class Materia(
    @Id
    @Column(name = "materia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "titulo_materia")
    var titulo: String,
    @Column(name = "professor")
    var professor: String,
    @Column(name = "descricao")
    var descricao: String,
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario
)