package br.com.pedule.business.process

import br.com.pedule.business.model.Anotacao
import br.com.pedule.services.repository.AnotacaoRepository
import br.com.pedule.services.repository.LinkRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping

@Service
class AnotacaoProcess {

    @Autowired
    private lateinit var repository: AnotacaoRepository

    @Autowired
    private lateinit var linkRepository: LinkRepository

    fun save(anotacao: Anotacao): Anotacao {
        anotacao.validar()
        val savedAnotacao = repository.save(anotacao)

        if (anotacao.link.isNotEmpty()) {
            val links = anotacao.link
            links.forEach { it.validar() }
            links.forEach { it.anotacao = savedAnotacao }
            links.forEach { linkRepository.save(it) }
        }

        return savedAnotacao
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun getByUserId(id: Long): List<Anotacao> {
        return repository.getByUsuarioId(id)
    }
}