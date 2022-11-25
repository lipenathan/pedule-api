package br.com.pedule.infra.api.security;

import br.com.pedule.business.model.Usuario;

/**
 * Classe de retorno após autenticação
 */
class AuthenticatedUser(val user: Usuario, val token: String)
