package br.com.pedule.business.process

import br.com.pedule.business.model.Usuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate

@SpringBootTest
class TesteUsuarioProcess {

    @Autowired
    private lateinit var usuarioProcess : UsuarioProcess

    //casos de erro
    @Test
    fun naoDeveCadastrarUsuarioSemNome() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(email = "augusto@gmail.com", dataNascimento = dataNasc, senha = "abc112")
        try {
            usuarioProcess.salvar(usuario)
            fail { "Não deve persistir usuário sem nome" }
        } catch (e:Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveCadastrarUsuarioSemEmail() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "Augusto", dataNascimento = dataNasc, senha = "abc112")
        try {
            usuarioProcess.salvar(usuario)
            fail { "Não deve persistir usuário sem e-mail" }
        } catch (e:Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveCadastrarUsuarioSemDataNascimento() {
        val usuario = Usuario(nome = "augusto", email = "augusto@gmail.com", senha = "abc112")
        try {
            usuarioProcess.salvar(usuario)
            fail { "Não deve persistir usuário sem data de nascimento" }
        } catch (e:Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveCadastrarUsuarioSemSenha() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "augusto", email = "felipe@gmail.com", dataNascimento = dataNasc)
        try {
            usuarioProcess.salvar(usuario)
            fail { "Não deve persistir usuário sem senha" }
        } catch (e:Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveAtivarUsuarioInexistente() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "testonildo1", email = "testonildo@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.ativar(usuario)
            fail { "Não deve ativar usuário não cadastrado" }
        } catch (e:Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveCadastrarUsuarioEmailExistente() {
        val dataNasc = LocalDate.of(2000, 10, 1)
        val usuario1 = Usuario(nome = "teste1", email = "felipe@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        val usuario2 = Usuario(nome = "teste2", email = "felipe@gmail.com", dataNascimento = dataNasc, senha = "efg312")
        try {
            usuarioProcess.salvar(usuario1)
            usuarioProcess.salvar(usuario2)
            fail {"não deve cadastrar dois usuários com o mesmo e-mail"}
        } catch (e : Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveLogarComUsuarioInexistente() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "teste11", email = "felipe1@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.login(usuario)
            fail { "Não deve logar com usuário inexistente" }
        } catch (e: Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveLogarComEmailInativo() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "teste11", email = "felipe123@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.salvar(usuario)
            usuarioProcess.login(usuario)
            fail { "Não deve logar com senha incorreta" }
        } catch (e: Exception) {
            println(e)
        }
    }

    @Test
    fun naoDeveLogarComSenhaDiferente() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "teste11", email = "felipe12@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.salvar(usuario)
            usuarioProcess.ativar(usuario)
            usuario.senha = "11"
            usuarioProcess.login(usuario)
            fail { "Não deve logar com senha incorreta" }
        } catch (e: Exception) {
            println(e)
        }
    }

    //casos de sucesso
    @Test
    fun deveCadastrarUsuarioComSucesso() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "teste", email = "felipe@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.salvar(usuario)
        } catch (e:Exception) {
            fail { "Deve cadastrar usuário com sucesso" }
        }
    }

    @Test
    fun deveAtivarUsuarioComSucesso() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "testonildo", email = "testonildo@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.salvar(usuario)
            usuarioProcess.ativar(usuario)
        } catch (e:Exception) {
            fail { "Deve ativar usuário com sucesso" }
        }
    }

    @Test
    fun deveLogarComSucesso() {
        val dataNasc = LocalDate.of(1996, 6, 15)
        val usuario = Usuario(nome = "teste", email = "felipe1@gmail.com", dataNascimento = dataNasc, senha = "abc123")
        try {
            usuarioProcess.salvar(usuario)
            usuarioProcess.ativar(usuario)
            val retorno = usuarioProcess.login(usuario)
            println(retorno)
        } catch (e: Exception) {
            fail { "Deve realizar login com sucesso" }
        }
    }
}