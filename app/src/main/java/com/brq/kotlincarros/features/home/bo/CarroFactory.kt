package com.brq.kotlincarros.features.home.bo

import com.brq.kotlincarros.R
import com.brq.kotlincarros.features.home.vo.CarroVO
import com.brq.kotlincarros.extensions.random
import java.util.*

class CarroFactory {
    companion object {
        val MODELOS = arrayOf("Palio", "Onix", "Gol", "Ka", "HB20")
        // Inicialização de Ranges - neste caso IntRange
        val PRECOS = 30000..90000
        val ANOS = 1990..2018
        // Criação fácil de List<...> através da função listOf
        val IMAGENS = listOf(R.drawable.carro1, R.drawable.carro2, R.drawable.carro3,
                R.drawable.carro4)

        fun criarCarro(): CarroVO {
            return CarroVO(
                    // Podemos referenciar os atributos que desejamos configurar
                    // em um construtor ou função diretamente como abaixo
                    modelo = MODELOS.get(Random().nextInt(MODELOS.size)),
                    preco = PRECOS.random().toDouble(),
                    ano = ANOS.random(),
                    imagemId = IMAGENS.random()
            )
        }
    }
}
