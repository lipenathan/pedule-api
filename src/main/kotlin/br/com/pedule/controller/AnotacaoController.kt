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

    @PostMapping("/novo")
    private fun new(@RequestBody anotacao: Anotacao): Anotacao {
        return anotacaoProcess.save(anotacao)
    }

    @PostMapping("/atualizar")
    private fun update(@RequestBody anotacao: Anotacao): Anotacao {
        return anotacaoProcess.save(anotacao)
    }

    @GetMapping("/listar/{id}")
    fun listar(@PathVariable id: Long): List<Anotacao> {
        return anotacaoProcess.getByUserId(id)
    }
}