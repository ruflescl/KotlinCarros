package com.brq.kotlincarros.features.login.bo

import com.brq.kotlincarros.features.login.api.LoginListener
import com.brq.kotlincarros.features.login.vo.UsuarioVO

class LoginBO(val loginListener: LoginListener) {

    // 1) Campos 'final' de java recebem o qualificador 'val' no Kotlin
    // 2) Criação fácil de um Array<...> através da função arrayOf
    private val CREDENCIAIS_FAKE = arrayOf(
            UsuarioVO("lyra@brq.com",
                    "12345",
                    "Rafael Lyra"),
            UsuarioVO("fernando@brq.com",
                    "54321",
                    "Fernando Martins"))

    fun realizarLogin(email: String, senha: String) {
        if (!emailValido(email)) {
            loginListener.erroEmailInvalido()
        }
        else if (!senhaTamanhoValido(senha)) {
            loginListener.erroSenhaCurta()
        }
        else {
            val usuarioModel = obterCredencial(email, senha)

            // Existe a possibilidade de deixar if's que são simples no java
            // mais simples ainda com o 'when'
            when (usuarioModel) {
                null -> loginListener.erroCredencialNaoExiste()
                else -> loginListener.loginSucesso(usuarioModel)
            }
        }
    }

    // Retornos de funções são movidos para o final da declaração da função
    // como no exemplo abaixo - retorna Boolean
    private fun emailValido(email: String): Boolean {
        return email.contains("@")
    }

    private fun senhaTamanhoValido(password: String): Boolean {
        return password.length > 4
    }

    // Tipos nullable: Quando
    private fun obterCredencial(email: String, senha: String): UsuarioVO? {
        // Função 'find' - é uma função que recebe como argumento o objeto que a
        // está invocando (neste caso é uma Array<UsuarioVO>), permitindo o acesso
        // a ele com o valor-parâmetro 'it', aplicando um predicado para realizar uma
        // busca no objeto passado como argumento e retornando somente um UsuarioVO
        // ou 'null' caso não encontre um elemento
        return CREDENCIAIS_FAKE.find { it.email == email && it.senha == senha }
    }
}