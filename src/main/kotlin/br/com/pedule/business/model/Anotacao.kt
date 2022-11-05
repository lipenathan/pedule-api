package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "TB_ANOTACAO")
data class Anotacao(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anotacao_id")
    var id: Long = 0,
    @Column(name = "titulo")
    var titulo: String,
    @Column(name = "descricao")
    var descricao: String = "",
    @Column(name = "lembrete")
    var lembrete: Boolean = false,
    @Column(name = "data_hora")
    var dataHorario: LocalDateTime? = null,
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "anotacao_link_id")
    var link: MutableList<Link> = mutableListOf(),
    @ManyToOne
    @JoinColumn(name = "usuario_anotacao_id")
    var usuario: Usuario? = null
) {
    fun validar() {
        if (titulo.isNullOrEmpty()) throw NegocioException("Título é obrigatório")
        if (lembrete) {
            if (dataHorario == null) throw NegocioException("Data e horário do lembrete é obrigatório")
        }
    }
}