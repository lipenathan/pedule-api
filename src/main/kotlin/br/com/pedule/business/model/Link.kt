package br.com.pedule.business.model

import javax.persistence.*

@Entity(name="TB_LINK")
data class Link(
    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "url")
    var url: String,
    @Column(name = "descricao")
    var descricao: String,
    @ManyToOne
    @JoinColumn(name = "anotacao_link_id")
    var anotacao : Anotacao
)