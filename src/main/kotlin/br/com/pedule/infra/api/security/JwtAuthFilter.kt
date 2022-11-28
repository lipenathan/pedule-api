package br.com.pedule.infra.api.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter(val jwtService: JwtService, val userService: UserServiceImpl) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")
         if (authorization != null && authorization.startsWith("Bearer")) {
            val token = authorization.split(" ")[1]
            val validToken = jwtService.validToken(token)
            if (validToken) {
                val loginUsuario = jwtService.getLoginUser(token)
                val userDetails = userService.loadUserByUsername(loginUsuario)
                val user = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                user.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = user
            }
        }
        filterChain.doFilter(request, response)
    }
}