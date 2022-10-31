package br.com.pedule.controller

import br.com.pedule.business.model.Atividade
import br.com.pedule.business.process.AtividadeProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/atividade")
class AtividadeController {

    @Autowired
    private lateinit var atividadeProcess: AtividadeProcess

    @PostMapping("/novo")
    private fun new(@RequestBody anotacao: Atividade): Atividade {
        return atividadeProcess.new(anotacao)
    }

    @PostMapping("/atualizar")
    private fun update(@RequestBody anotacao: Atividade): Atividade {
        return atividadeProcess.update(anotacao)
    }

    @GetMapping("/listar/{id}")
    fun listar(@PathVariable id: Long): List<Atividade> {
        return atividadeProcess.getByUserId(id)
    }
}