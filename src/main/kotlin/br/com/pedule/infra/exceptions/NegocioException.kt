package br.com.pedule.infra.exceptions

class NegocioException() : Exception() {
    constructor(message : String) : this() {
        throw Exception(message)
    }
}