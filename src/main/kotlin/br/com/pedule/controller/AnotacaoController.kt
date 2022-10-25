package br.com.pedule.controller

import br.com.pedule.business.model.Anotacao
import br.com.pedule.business.process.AnotacaoProcess
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/anotacao")
class AnotacaoController {

    private lateinit var anotacaoProcess: AnotacaoProcess

    @PostMapping("/novo")
    private fun new(@RequestBody anotacao: Anotacao): Anotacao {
        return anotacaoProcess.save(anotacao)
    }

    @PostMapping("/atualizar")
    private fun update(@RequestBody anotacao: Anotacao): Anotacao {
        return anotacaoProcess.save(anotacao)
    }


}