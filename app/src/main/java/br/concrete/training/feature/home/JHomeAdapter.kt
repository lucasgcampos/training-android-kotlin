package br.concrete.training.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import br.concrete.training.data.model.Item
import br.concrete.training.R
import br.concrete.training.R.id.task

import br.concrete.training.feature.home.Api.fetchDefaultItems

/**
 * @author Lucas Campos
 */

class JHomeAdapter : RecyclerView.Adapter<JHomeAdapter.ViewHolder>() {

    private val items = fetchDefaultItems()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun addItem(item: Item) {
        items.add(item)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val task: TextView = itemView.findViewById(R.id.task)
        private val description: TextView = itemView.findViewById(R.id.description)

        internal fun bindView(item: Item) {
            task.text = item.task
            description.text = item.description
        }

    }
}

internal object Api {
    fun fetchDefaultItems(): ArrayList<Item> {
        val items = ArrayList<Item>()

        items.add(Item("Estudar Kotlin", "Estudar a estrutura básica da linguagem"))
        items.add(Item("Estudar Dagger2", "Estudar o conceito de injeção de dependência e depois entender como funciona o dagger2 "))

        return items
    }
}
