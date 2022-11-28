package br.com.pedule.infra.api.security

import br.com.pedule.business.model.Usuario
import br.com.pedule.infra.exceptions.NegocioException
import br.com.pedule.services.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var repository: UsuarioRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    @Autowired
    private lateinit var jwtService: JwtService

    private lateinit var usuario: Usuario

    fun save(usuario: Usuario): Usuario {
        usuario.validar()
        validateNonExistingUser(usuario.email)
        usuario.nome = upperCaseFirstLetters(usuario.nome)
        usuario.senha = encoder.encode(usuario.senha)
        return repository.save(usuario)
    }

    private fun upperCaseFirstLetters(name: String): String {
        val names = name.split(" ")
        val upperNames = mutableListOf<String>()
        for (item in names) {
            upperNames.add(item.replaceRange(0,1,item[0].uppercase()))
        }
        var upperName = ""
        for ((index, value) in upperNames.withIndex()) {
            upperName += if (index == 0) {//verifica se é o primeiro nome
                value
            } else {
                " ${value}"
            }
        }
        return upperName
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

    fun login(usuario: Usuario): AuthenticatedUser {
        val userDetails = loadUserByUsername(usuario.email)
        val valid = encoder.matches(usuario.senha, userDetails.password)
        if (valid) {
            val token = jwtService.generateToken(this.usuario)
            return AuthenticatedUser(this.usuario, token)
        } else {
            throw NegocioException("Senha inválida")
        }
    }

    override fun loadUserByUsername(email: String): UserDetails {
        this.usuario = repository.findUsuarioByEmail(email) ?: throw NegocioException("usuário não encontrado")
        return User.builder().password(usuario.senha)
            .username(usuario.email)
            .authorities(arrayListOf())
            .build()
    }

    //métodos privados
    private fun validateNonExistingUser(email: String) {
        val usuario = repository.findUsuarioByEmail(email)
        if (usuario != null) throw NegocioException("Email já cadastrado")
    }
}