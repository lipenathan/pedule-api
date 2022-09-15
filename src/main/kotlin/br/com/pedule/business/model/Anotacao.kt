package br.com.pedule.business.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name="TB_ANOTACAO")
data class Anotacao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anotacao_id")
    var id: Long,
    @Column(name = "titulo")
    var titulo: String,
    @Column(name = "descricao")
    var descricao: String,
    @Column(name = "lembrete")
    var lembrete: Boolean,
    @Column(name = "data_hora")
    var dataHorario: LocalDateTime,
    @OneToMany
    @JoinColumn(name = "link_id")
    var link : MutableList<Link>,
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario : Usuario
)