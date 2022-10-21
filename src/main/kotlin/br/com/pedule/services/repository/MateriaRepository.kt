package br.com.pedule.services.repository

import br.com.pedule.business.model.Materia
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MateriaRepository : JpaRepository<Materia, Long> {
    fun getByUsuarioId(id: Long): List<Materia>
}