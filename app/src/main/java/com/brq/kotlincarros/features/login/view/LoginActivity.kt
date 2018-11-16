package com.brq.kotlincarros.features.login.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.brq.kotlincarros.R
import com.brq.kotlincarros.features.home.view.HomeActivity
import com.brq.kotlincarros.features.login.api.LoginListener
import com.brq.kotlincarros.features.login.bo.LoginBO
import com.brq.kotlincarros.features.login.vo.UsuarioVO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginListener {
    // Campos variaveis que não podem ser instanciados imediatamente podem
    // receber o qualificador 'lateinit' (inicialização futura)
    private lateinit var loginBO: LoginBO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inicializarRegras()
        configurarViews()
    }

    private fun realizarLogin() {
        limparErros()

        // 1) Com o Kotlin Extensions para Android, fica muito simples referenciar
        // os campos de layout no código Kotlin com o id do campo diretamente
        // 2) Campos que somente são acessíveis no java através do POJO (get, set)
        // podem ser acessados diretamente no Kotlin
        val emailDigitado = edt_email.text.toString()
        val senhaDigitada = edt_senha.text.toString()

        loginBO.realizarLogin(emailDigitado, senhaDigitada)
    }

    private fun configurarViews() {
        // 1) Lambdas substituem chamadas convencionais do Java
        // 2) Campos que não são importantes ou opcionais pro contexto da chamada
        // podem ser substituidos por '_'
        edt_senha.setOnEditorActionListener({ _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                realizarLogin()
            }
            false
        })

        // Mais um caso de um lambda substituindo uma chamada convencional
        btn_entrar.setOnClickListener { realizarLogin() }
    }

    private fun inicializarRegras() {
        loginBO = LoginBO(this)
    }

    private fun limparErros() {
        edt_email.error = null
        edt_senha.error = null
    }

    override fun loginSucesso(usuarioVO: UsuarioVO) {
        Toast.makeText(this,
                "Login efetuado com sucesso!",
                Toast.LENGTH_SHORT)
                .show()

        abrirHome(usuarioVO)
    }

    private fun abrirHome(usuarioVO: UsuarioVO) {
        // Passamos a classe da activity a ser aberta da maneira abaixo,
        // referenciando a classe como 'class.java' para respeitar o parâmetro
        // na assinatura do método 'legado', que não aceita classes kotlin
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(HomeActivity.EXTRA_USUARIO_APELIDO, usuarioVO.apelido)
        startActivity(intent)
        finish()
    }

    override fun erroCredencialNaoExiste() {
        edt_email.error = getString(R.string.credencial_nao_existe)
        edt_email.requestFocus()
    }

    override fun erroEmailInvalido() {
        edt_email.error = getString(R.string.email_invalido)
        edt_email.requestFocus()
    }

    override fun erroSenhaCurta() {
        edt_senha.error = getString(R.string.senha_invalida_muito_curta)
        edt_senha.requestFocus()
    }
}
