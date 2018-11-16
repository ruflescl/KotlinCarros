package com.brq.kotlincarros.extensions

import java.util.*

// Extension da classe List: Adicionamos uma nova função para a classe List<T>
// já existente
fun <T> List<T>.random(): T {
    // ifs com returns inline
    return if (size == 0) throw IndexOutOfBoundsException()
    else get(Random().nextInt(size - 1))

    /* Maneira alternativa do código acima
    when(size) {
        0 -> throw IndexOutOfBoundsException()
        else -> return get(Random().nextInt(size - 1))
    }
     */
}