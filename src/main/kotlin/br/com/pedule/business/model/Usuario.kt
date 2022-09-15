package br.com.pedule.business.model

import br.com.pedule.infra.exceptions.NegocioException
import java.time.LocalDate
import javax.persistence.*

@Entity(name = "TB_USUARIO")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private var id: Long = 0,
    @Column(name = "nome_usuario")
    var nome: String = "",
    @Column(name = "email")
    var email: String = "",
    @Column(name = "data_nascimento")
    var dataNascimento: LocalDate? = null,
    @Column(name = "instituicao")
    var instituicao: String = "",
    @Column(name = "email_ativo")
    var emailAtivo: Boolean = false,
    @Column(name = "senha")
    var senha : String = ""
) {
    fun validar() {
        if (nome.isEmpty()) throw NegocioException("Nome é obrigatório")
        if (email.isEmpty()) throw NegocioException("E-mail é obrigatório")
        if (dataNascimento == null) throw NegocioException("Data de nascimento é obrigatório")
        if (senha.isEmpty()) throw NegocioException("Senha é obrigatório")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (nome != other.nome) return false
        if (email != other.email) return false
        if (dataNascimento != other.dataNascimento) return false
        if (senha != other.senha) return false

        return true
    }

    override fun hashCode(): Int {
        var result = nome.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (dataNascimento?.hashCode() ?: 0)
        result = 31 * result + senha.hashCode()
        return result
    }
}