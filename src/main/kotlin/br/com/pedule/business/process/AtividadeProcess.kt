package br.com.pedule.business.process

import br.com.pedule.business.model.Atividade
import br.com.pedule.services.repository.AtividadeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AtividadeProcess {

    @Autowired
    private lateinit var repository: AtividadeRepository

    fun new(atividade: Atividade): Atividade {
        atividade.validar()
        return repository.save(atividade)
    }

    fun update(atividade: Atividade): Atividade {
        atividade.validar()
        return repository.save(atividade)
    }

    fun getByUserId(id: Long): List<Atividade> {
        return repository.getByUsuarioId(id)
    }
}