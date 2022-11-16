package br.com.pedule.controller

import br.com.pedule.business.model.Materia
import br.com.pedule.business.process.MateriaProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/materia")
class MateriaController {

    @Autowired
    private lateinit var materiaProcess: MateriaProcess

    @PostMapping("/salvar")
    fun save(@RequestBody materia: Materia): Materia {
        return materiaProcess.save(materia)
    }

    @DeleteMapping("/deletar/{id}")
    fun delete(@PathVariable id: Long) {
        materiaProcess.delete(id)
    }

    @GetMapping("/listar/{id}")
    fun list(@PathVariable id: Long): List<Materia> {
        return materiaProcess.getByUserId(id)
    }
}