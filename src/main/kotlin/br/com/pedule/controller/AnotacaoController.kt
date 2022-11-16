package br.com.pedule.controller

import br.com.pedule.business.model.Anotacao
import br.com.pedule.business.model.Materia
import br.com.pedule.business.process.AnotacaoProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/anotacao")
class AnotacaoController {

    @Autowired
    private lateinit var anotacaoProcess: AnotacaoProcess

    @PostMapping("/salvar")
    private fun save(@RequestBody anotacao: Anotacao): Anotacao {
        return anotacaoProcess.save(anotacao)
    }

    @DeleteMapping("/deletar/{id}")
    private fun delete(@PathVariable id: Long) {
        anotacaoProcess.delete(id)
    }

    @GetMapping("/listar/{id}")
    fun list(@PathVariable id: Long): List<Anotacao> {
        return anotacaoProcess.getByUserId(id)
    }
}