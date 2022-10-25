package br.com.pedule.services.repository

import br.com.pedule.business.model.Anotacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnotacaoRepository : JpaRepository<Anotacao, Long> {
    fun getByUsuarioId(id: Long): List<Anotacao>
}