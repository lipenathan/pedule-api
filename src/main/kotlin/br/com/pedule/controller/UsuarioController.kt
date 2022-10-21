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
    fun novo(@RequestBody usuario: Usuario): Usuario {
        return usuarioProcessos.novo(usuario)
    }

    @PostMapping("/atualizar")
    fun atualizar(@RequestBody usuario: Usuario): Usuario {
        return usuarioProcessos.atualizar(usuario)
    }

    @PostMapping("/ativar")
    fun ativar(@RequestBody usuario: Usuario) {
        usuarioProcessos.ativar(usuario)
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario : Usuario) : Usuario{
        return usuarioProcessos.login(usuario)
    }
}