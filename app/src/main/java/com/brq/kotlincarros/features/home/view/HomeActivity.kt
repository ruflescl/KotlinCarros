package com.brq.kotlincarros.features.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.brq.kotlincarros.R
import com.brq.kotlincarros.features.home.bo.HomeBO
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    // 'companion objects' atuam, superficialmente, como acessores estáticos da classe
    companion object {
        val EXTRA_USUARIO_APELIDO = "EXTRA_USUARIO_APELIDO"
    }

    // Inicializador 'lazy': somente instancia o objeto caso for acessado
    private val listaCarrosAdapter: ListaCarrosAdapter by lazy {
        ListaCarrosAdapter(homeBO.obterCarros(), this)
    }

    lateinit var homeBO: HomeBO
    lateinit var apelido: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        inicializarRegras()
        configurarViews()
    }

    private fun inicializarRegras() {
        homeBO = HomeBO()
        apelido = intent.getStringExtra(EXTRA_USUARIO_APELIDO)
    }

    private fun configurarViews() {
        fab_adicionar_item.setOnClickListener { view ->
            adicionarNovoCarro()
        }

        tv_bem_vindo.text = getString(R.string.bem_vindo, apelido)

        rv_lista_carros.layoutManager = LinearLayoutManager(this)
        rv_lista_carros.adapter = listaCarrosAdapter
    }

    private fun adicionarNovoCarro() {
        homeBO.adicionarNovoCarro()
        listaCarrosAdapter.configurarLista(homeBO.obterCarros())
        rv_lista_carros.layoutManager.scrollToPosition(
                rv_lista_carros.layoutManager.itemCount - 1)
    }
}
