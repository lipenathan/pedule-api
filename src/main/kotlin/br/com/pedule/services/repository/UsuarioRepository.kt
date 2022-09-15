package br.com.pedule.services.repository

import br.com.pedule.business.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findUsuarioByEmail(email: String) : Usuario?
}