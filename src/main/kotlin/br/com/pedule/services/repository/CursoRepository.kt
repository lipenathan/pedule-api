package br.com.pedule.services.repository

import br.com.pedule.business.model.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {
    fun getByUsuarioId(id: Long): List<Curso>
}