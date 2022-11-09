package br.com.pedule.business.process

import br.com.pedule.business.model.Materia
import br.com.pedule.services.repository.LinkRepository
import br.com.pedule.services.repository.MateriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MateriaProcess {

    @Autowired
    private lateinit var repository: MateriaRepository

    fun new(materia: Materia): Materia {
        materia.validar()
        return repository.save(materia)
    }

    fun update(materia: Materia): Materia {
        materia.validar()
        return repository.save(materia)
    }

    fun getByUserId(id: Long): List<Materia> {
        return repository.getByUsuarioId(id)
    }
}