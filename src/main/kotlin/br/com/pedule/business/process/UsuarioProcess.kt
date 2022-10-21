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

    fun novo(usuario: Usuario): Usuario {
        usuario.validar()
        validarUsuarioNaoExistente(usuario.email)
        return repository.save(usuario)
    }

    fun atualizar(usuario: Usuario): Usuario {
        usuario.validar()
        return repository.save(usuario)
    }

    fun ativar(usuario: Usuario) {
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
        validarUsuario(usuarioBase, usuario)
        return usuarioBase!!
    }

    //métodos privados

    private fun validarUsuario(usuarioBase: Usuario?, usuario: Usuario): Boolean {
        if (usuarioBase == null) throw NegocioException("Usuário não cadastrado")
        if (!usuarioBase.emailAtivo) throw NegocioException("Usuário sem e-mail ativo")
        return if (usuarioBase.senha == usuario.senha) true else throw NegocioException("Senha inválida")
    }

    private fun validarUsuarioNaoExistente(email: String) {
        val usuario = repository.findUsuarioByEmail(email)
        if (usuario != null) throw NegocioException("Email já cadastrado")
    }
}
