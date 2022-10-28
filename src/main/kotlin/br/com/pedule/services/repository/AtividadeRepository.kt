package br.com.pedule.services.repository

import br.com.pedule.business.model.Atividade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AtividadeRepository : JpaRepository<Atividade, Long> {
    fun getByUsuarioId(id: Long): List<Atividade>
}