package br.com.pedule.business.process

import br.com.pedule.business.model.Curso
import br.com.pedule.services.repository.CursoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CursoProcess {

    @Autowired
    private lateinit var repository: CursoRepository

    fun save(curso: Curso): Curso {
        curso.validar()
        return repository.save(curso)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun getByUserId(id: Long): List<Curso> {
        return repository.getByUsuarioId(id)
    }
}