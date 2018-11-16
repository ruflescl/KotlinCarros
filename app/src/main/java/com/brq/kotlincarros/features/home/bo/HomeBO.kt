package com.brq.kotlincarros.features.home.bo

import com.brq.kotlincarros.features.home.vo.CarroVO

class HomeBO {
    private val listaCarros = ArrayList<CarroVO>()

    fun obterCarros(): List<CarroVO> {
        if (listaCarros.isEmpty()) {
            mockarListaCarros()
        }
        return listaCarros
    }

    fun adicionarNovoCarro() {
        listaCarros.add(CarroFactory.criarCarro())
    }

    private fun mockarListaCarros() {
        (0..9).forEach {
            listaCarros.add(CarroFactory.criarCarro())
        }
    }
}