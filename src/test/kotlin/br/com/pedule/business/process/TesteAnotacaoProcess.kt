package br.com.pedule.business.process

import br.com.pedule.business.model.Anotacao
import br.com.pedule.business.model.Link
import br.com.pedule.business.model.Usuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class TesteAnotacaoProcess {

    @Autowired
    private lateinit var anotacaoProcess: AnotacaoProcess

    //casos de erro
    @Test
    fun naoDeveCadastrarAnotacaoSemTitulo() {
        val anotacao = Anotacao(titulo = "")
        try {
            anotacaoProcess.save(anotacao)
            fail { "Não deve salvar anotação sem título" }
        } catch (e: Exception) {
            print(e.message)
        }
    }

    @Test
    fun naoDeveCadastrarLembreteSemDataHorario() {
        val anotacao = Anotacao(titulo = "Lembrete", lembrete = true)
        try {
            anotacaoProcess.save(anotacao)
            fail { "Não deve salvar lembrete sem data e horário" }
        } catch (e: Exception) {
            print(e.message)
        }
    }

    //casos de sucesso
    @Test
    fun deveCadastrarAnotacaoComSucesso() {
        val data = LocalDateTime.of(2022, 7, 20, 17, 30)
        val usuario = Usuario(id = 16)
        val anotacao = Anotacao(
            titulo = "teste 3", descricao = "anotação teste 3",
            lembrete = true, dataHorario = data, usuario = usuario
        )
        try {
            anotacaoProcess.save(anotacao)
        } catch (e: Exception) {
            fail { "Deve cadastrar anotação com sucesso" }
        }
    }

    @Test
    fun deveBuscarAnotacoesPeloIdDoUsuario() {
        try {
            val anotacaos = anotacaoProcess.getByUserId(6)
            assert(anotacaos.isNotEmpty())
            println(anotacaos)
        } catch (e: Exception) {
            fail { "Deve buscar anotação sem erro -> ${e.message}" }
        }
    }

    @Test
    fun deveSalvarAnotacaoComLink() {
        val data = LocalDateTime.of(2022, 7, 20, 17, 30)
        val usuario = Usuario(id = 6)
        val anotacao = Anotacao(
            titulo = "teste 3", descricao = "anotação teste linkeers",
            lembrete = true, dataHorario = data, usuario = usuario, link = mutableListOf(Link(url = "http://dajshdkajshd.combo"))
        )
        try {
            anotacaoProcess.save(anotacao)
        } catch (e: Exception) {
            fail { "Deve cadastrar anotação com sucesso" }
        }
    }
}