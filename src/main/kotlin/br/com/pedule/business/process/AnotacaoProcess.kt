package br.com.pedule.business.process

import br.com.pedule.business.model.Anotacao
import br.com.pedule.services.repository.AnotacaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnotacaoProcess {

    @Autowired
    private lateinit var repository: AnotacaoRepository

    fun save(anotacao: Anotacao): Anotacao {
        anotacao.validar()
        return repository.save(anotacao)
    }

    fun getByUserId(id: Long): List<Anotacao> {
        return repository.getByUsuarioId(id)
    }
}