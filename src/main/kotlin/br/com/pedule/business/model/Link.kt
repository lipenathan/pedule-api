package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity(name="TB_LINK")
data class Link(
    @Id
    @Column(name = "link_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "url")
    var url: String,
    @Column(name = "descricao")
    var descricao: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "anotacao_link_id")
    var anotacao : Anotacao? = null
) {
    fun validar() {
        if(url.isNullOrEmpty()) throw NegocioException("A URL é obrigatória")
    }

    override fun toString(): String {
        return "Link(id=$id, url='$url', descricao='$descricao', anotacao=${anotacao?.id})"
    }

}