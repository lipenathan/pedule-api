package br.com.pedule.infra.api.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userDetailService: UserServiceImpl

    @Autowired
    private lateinit var jwtService: JwtService

    @Bean
    fun jwtFilter(): OncePerRequestFilter {
        return JwtAuthFilter(jwtService, userDetailService)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.let {
            it.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
        }
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/novo").permitAll()
                .antMatchers("/usuario/login").permitAll()
                .antMatchers(
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/v3/pedule-docs",
                    "/pedule-docs.html",
                    "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(CorsFilterConfig(), ChannelProcessingFilter::class.java)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
        }
    }
}