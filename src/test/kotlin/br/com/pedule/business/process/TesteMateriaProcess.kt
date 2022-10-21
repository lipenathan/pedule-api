package br.com.pedule.business.process

import br.com.pedule.business.model.DiaSemana
import br.com.pedule.business.model.Materia
import br.com.pedule.business.model.SemanaHorario
import br.com.pedule.business.model.Usuario
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalTime

@SpringBootTest
class TesteMateriaProcess {

    @Autowired
    private lateinit var process: MateriaProcess

    //casos de erro
    @Test
    fun naoDeveCadastrarMateriaSemTítulo() {

    }

    //casos de sucesso
    @Test
    fun deveCadastrarMateria() {
        val semanaHorario = SemanaHorario(horario = LocalTime.of(19, 30), semana = DiaSemana(1))
        val usuario = Usuario(id = 6)
        val materia = Materia(
            titulo = "Primeira matéria",
            descricao = "primeira materia de teste",
            semanaHorario = listOf(semanaHorario),
            usuario = usuario
        )
        try {
            process.novo(materia)
        } catch (e: Exception) {
            fail ("Deve cadastrar sem erro  -> ${e.message}")
        }
    }
}