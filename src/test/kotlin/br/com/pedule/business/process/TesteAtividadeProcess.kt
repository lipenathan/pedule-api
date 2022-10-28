package br.com.pedule.business.process

import br.com.pedule.business.model.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class TesteAtividadeProcess {

    @Autowired
    private lateinit var process: AtividadeProcess

    //casos de erro
    @Test
    fun naoDeveCadastrarAtividadeSemTitulo() {

    }

    //casos de sucesso
    @Test
    fun deveCadastrarAtividadeComSucesso() {
        val usuario = Usuario(16)
        val dataHorario = LocalDateTime.of(2022, 6, 1, 22, 15)
        val atividade = Atividade(usuario = usuario, titulo = "Teste anotação", dataHorarioEntrega = dataHorario)
        try {
            process.new(atividade)
            println(atividade)
        } catch (e: Exception) {
            fail { "Deve cadastrar atividade com sucesso -> ${e.message}" }
        }
    }

    @Test
    fun deveCadastrarAtividadeComMateriaComSucesso() {
        val usuario = Usuario(16)
        val materia = Materia(id = 11, usuario = usuario)
        val dataHorario = LocalDateTime.of(2022, 6, 1, 22, 15)
        val atividade = Atividade(usuario = usuario, titulo = "Teste anotação",
            dataHorarioEntrega = dataHorario, materia = materia)
        try {
            process.new(atividade)
            println(atividade)
        } catch (e: Exception) {
            fail { "Deve cadastrar atividade com sucesso -> ${e.message}" }
        }
    }

    @Test
    fun deveCadastrarAtividadeComCursoComSucesso() {
        val usuario = Usuario(16)
        val curso = Curso(usuario = usuario)
        curso.usuario = usuario
        val dataHorario = LocalDateTime.of(2022, 6, 1, 22, 15)
        val atividade = Atividade(usuario = usuario, titulo = "Teste anotação",
            dataHorarioEntrega = dataHorario, curso = curso)
        try {
            process.new(atividade)
            println(atividade)
        } catch (e: Exception) {
            fail { "Deve cadastrar atividade com sucesso -> ${e.message}" }
        }
    }
}