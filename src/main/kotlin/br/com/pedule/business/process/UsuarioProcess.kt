package br.com.pedule.business.process;

import br.com.pedule.business.model.Usuario;
import br.com.pedule.infra.exceptions.NegocioException
import br.com.pedule.services.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service;

@Service
class UsuarioProcess {

    @Autowired
    private lateinit var repository: UsuarioRepository

    fun save(usuario: Usuario): Usuario {
        usuario.validar()
        validateNonExistingUser(usuario.email)
        return repository.save(usuario)
    }

    fun activate(usuario: Usuario) {
        val usuarioByEmail = repository.findUsuarioByEmail(usuario.email)
        if (usuarioByEmail == usuario && !usuarioByEmail.emailAtivo) {
            usuarioByEmail.emailAtivo = true
        } else {
            throw NegocioException("E-mail ainda não cadastrado")
        }
        repository.save(usuarioByEmail)
    }

    fun login(usuario: Usuario): Usuario {
        val usuarioBase = repository.findUsuarioByEmail(usuario.email)
        validateUser(usuarioBase, usuario)
        return usuarioBase!!
    }

    //métodos privados

    private fun validateUser(usuarioBase: Usuario?, usuario: Usuario): Boolean {
        if (usuarioBase == null) throw NegocioException("Usuário não cadastrado")
        if (!usuarioBase.emailAtivo) throw NegocioException("Usuário sem e-mail ativo")
        return if (usuarioBase.senha == usuario.senha) true else throw NegocioException("Senha inválida")
    }

    private fun validateNonExistingUser(email: String) {
        val usuario = repository.findUsuarioByEmail(email)
        if (usuario != null) throw NegocioException("Email já cadastrado")
    }
}
