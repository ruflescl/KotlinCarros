package com.brq.kotlincarros.features.login.api

import com.brq.kotlincarros.features.login.vo.UsuarioVO

interface LoginListener {
    fun loginSucesso(usuarioVO: UsuarioVO)

    fun erroEmailInvalido()
    fun erroCredencialNaoExiste()
    fun erroSenhaCurta()
}