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

    @PostMapping("/salvar")
    private fun save(@RequestBody anotacao: Atividade): Atividade {
        return atividadeProcess.save(anotacao)
    }

    @DeleteMapping("deletar/{id}")
    private fun delete(@PathVariable id: Long) {
        atividadeProcess.delete(id)
    }

    @GetMapping("/listar/{id}")
    fun list(@PathVariable id: Long): List<Atividade> {
        return atividadeProcess.getByUserId(id)
    }
}