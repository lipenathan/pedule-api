package br.com.pedule.controller

import br.com.pedule.business.model.Usuario
import br.com.pedule.business.process.UsuarioProcess
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @Autowired
    private lateinit var usuarioProcessos: UsuarioProcess

    @PostMapping("/novo")
    fun save(@RequestBody usuario: Usuario): Usuario {
        return usuarioProcessos.save(usuario)
    }

    @PostMapping("/ativar")
    fun activate(@RequestBody usuario: Usuario) {
        usuarioProcessos.activate(usuario)
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario : Usuario) : Usuario{
        return usuarioProcessos.login(usuario)
    }
}