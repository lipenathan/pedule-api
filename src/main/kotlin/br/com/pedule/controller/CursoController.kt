package br.com.pedule.controller

import br.com.pedule.business.model.Curso
import br.com.pedule.business.process.CursoProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/curso")
class CursoController {

    @Autowired
    private lateinit var process: CursoProcess

    @PostMapping("/salvar")
    fun save(@RequestBody curso: Curso): Curso {
        return process.save(curso)
    }

    @DeleteMapping("/deletar/{id}")
    fun delete(@PathVariable id: Long) {
        process.delete(id)
    }

    @GetMapping("/listar/{id}")
    fun save(@PathVariable id: Long): List<Curso> {
        return process.getByUserId(id)
    }
}