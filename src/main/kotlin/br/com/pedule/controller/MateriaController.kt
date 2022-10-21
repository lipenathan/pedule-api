package br.com.pedule.controller

import br.com.pedule.business.model.Materia
import br.com.pedule.business.model.Usuario
import br.com.pedule.business.process.MateriaProcess
import br.com.pedule.business.process.UsuarioProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/materia")
class MateriaController {

    @Autowired
    private lateinit var materiaProcess: MateriaProcess

    @PostMapping("/novo")
    fun salvar(@RequestBody materia: Materia): Materia {
        return materiaProcess.novo(materia)
    }

    @PostMapping("/atualizar")
    fun atualizar(@RequestBody materia: Materia): Materia {
        return materiaProcess.atualizar(materia)
    }

    @GetMapping("/listar")
    fun ativar(@RequestBody id: Long): List<Materia> {
        return materiaProcess.getByUserId(id)
    }
}