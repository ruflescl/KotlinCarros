package com.brq.kotlincarros.features.login.vo

// Classes data são classes com propósito único: guardar dados.
data class UsuarioVO(val email: String,
                     val senha: String,

                     // Podemos definir valores default para parâmetros que desejamos
                     // omitir na inicialização
                     val apelido: String = "Fulano")