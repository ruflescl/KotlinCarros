package com.brq.kotlincarros.extensions

import java.util.*

// Extension da classe IntRange: Adicionamos uma nova função para a classe IntRange
// já existente
fun IntRange.random() =
        Random().nextInt((endInclusive + 1) - start) +  start
