package br.concrete.training.feature.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.concrete.training.R
import br.concrete.training.data.model.Item2
import br.concrete.training.feature.fetchDefaultItems

/**
 * Created by eliete on 2/16/18.
 */
class HomeAdapter : RecyclerView.Adapter<TasksViewHolder>() {

    private var items = fetchDefaultItems()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TasksViewHolder {
        val layout = LayoutInflater.from(parent?.context).inflate(R.layout.item_home, parent, false)
        return TasksViewHolder(layout)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TasksViewHolder?, position: Int) {
        holder?.bindView(items[position])
    }

    fun includeItemList(item: Item2) {
        items.add(item)
        notifyDataSetChanged()
    }
}