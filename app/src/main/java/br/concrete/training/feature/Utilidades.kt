package br.concrete.training.feature

import br.concrete.training.data.model.Item2
import java.util.ArrayList

/**
 * Created by eliete on 2/16/18.
 */

fun fetchDefaultItems(): ArrayList<Item2> {
    val items = ArrayList<Item2>()

    items.add(Item2("Estudar Kotlin", "Estudar a estrutura básica da linguagem"))
    items.add(Item2("Estudar Dagger2",
            "Estudar o conceito de injeção de dependência e depois entender como funciona o dagger2 "))

    return items
}
