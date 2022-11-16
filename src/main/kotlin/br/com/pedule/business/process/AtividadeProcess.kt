package br.com.pedule.business.process

import br.com.pedule.business.model.Atividade
import br.com.pedule.services.repository.AtividadeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZoneOffset

@Service
class AtividadeProcess {

    @Autowired
    private lateinit var repository: AtividadeRepository

    fun save(atividade: Atividade): Atividade {
        atividade.validar()
        return repository.save(atividade)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun getByUserId(id: Long): List<Atividade> {
        return repository.getByUsuarioId(id)
    }
}