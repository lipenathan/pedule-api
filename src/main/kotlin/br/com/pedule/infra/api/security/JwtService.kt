package br.com.pedule.infra.api.security

import br.com.pedule.business.model.Usuario
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class JwtService {

    companion object {
        const val EXPIRATION = 30L
        const val SIGN_KEY = "761da7c6-a50c-43de-870b-9f67669e46ac"
    }

    fun generateToken(user: Usuario): String {
        val date = LocalDateTime.now().plusMinutes(EXPIRATION)
        val instant = date.atZone(ZoneId.systemDefault()).toInstant()
        val expiration = Date.from(instant)
        return Jwts.builder()
            .setSubject(user.email)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, SIGN_KEY)
            .compact()
    }

    fun getTokenClaims(token: String): Claims {
        return Jwts
            .parser()
            .setSigningKey(SIGN_KEY)
            .parseClaimsJws(token)
            .body
    }

    fun getLoginUser(token: String): String {
        return getTokenClaims(token).subject as String
    }

    fun validToken(token: String): Boolean {
        try {
            val claims = getTokenClaims(token)
            val date = claims.expiration
            val localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            return localDateTime.isAfter(LocalDateTime.now())
        } catch (e: Exception) {
            return false
        }
    }
}