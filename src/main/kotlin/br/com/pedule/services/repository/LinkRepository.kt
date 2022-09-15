package br.com.pedule.services.repository

import br.com.pedule.business.model.Link
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LinkRepository : JpaRepository<Link, Long>