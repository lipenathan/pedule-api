package br.com.pedule.controller

import br.com.pedule.business.model.Usuario
import br.com.pedule.infra.api.security.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/usuario")
class UsuarioController {

//    @Autowired
//    private lateinit var usuarioProcessos: UsuarioProcess

    @Autowired
    private lateinit var usuarioService: UserServiceImpl

    @PostMapping("/novo")
    fun save(@RequestBody usuario: Usuario): Usuario {
        return usuarioService.save(usuario)
    }

    @PostMapping("/ativar")
    fun activate(@RequestBody usuario: Usuario) {
        usuarioService.activate(usuario)
    }

    @PostMapping("/login")
    fun login(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        try {
            val authUser = usuarioService.login(usuario)
            val headers = HttpHeaders()
            headers.set("access_token", authUser.token)
            return ResponseEntity(authUser.user, headers, HttpStatus.OK)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, e.message)
        }
    }
}