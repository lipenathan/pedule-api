package br.com.pedule.business.model

import javax.persistence.*

@Entity(name="TB_LINK")
data class Link(
    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "link")
    var url: String,
    @ManyToOne
    @JoinColumn(name = "anotacao_link_id")
    var anotacao : Anotacao
)