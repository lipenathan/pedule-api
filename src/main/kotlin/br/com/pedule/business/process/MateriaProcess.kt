package br.com.pedule.business.process

import br.com.pedule.business.model.Materia
import br.com.pedule.services.repository.MateriaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MateriaProcess {

    @Autowired
    private lateinit var repository: MateriaRepository

    fun save(materia: Materia): Materia {
        materia.validar()
        return repository.save(materia)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun getByUserId(id: Long): List<Materia> {
        return repository.getByUsuarioId(id)
    }
}