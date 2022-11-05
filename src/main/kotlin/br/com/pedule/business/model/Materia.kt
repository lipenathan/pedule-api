package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import javax.persistence.*

@Entity(name="TB_MATERIA")
data class Materia(
    @Id
    @Column(name = "materia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "titulo_materia")
    var titulo: String = "",
    @Column(name = "professor")
    var professor: String = "",
    @Column(name = "descricao")
    var descricao: String = "",
    @Column(name = "cor")
    var cor: String = "",
    @ManyToOne
    @JoinColumn(name = "usuario_materia_id")
    var usuario: Usuario,
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "TB_SEMANA_HORA_MATERIA",
        joinColumns = [JoinColumn(name = "MATERIA_ID")],
        inverseJoinColumns = [JoinColumn(name = "SEMANA_HORA_SEM_HOR_MAT_ID")]
    )
    var semanaHorario: List<SemanaHorario> = listOf()
) {
    fun validar() {
        if (titulo.isEmpty()) throw NegocioException("Título da matéria é obrigatório")
        if (semanaHorario.isEmpty()) throw NegocioException("Dia da semana é obrigatório")
    }
}