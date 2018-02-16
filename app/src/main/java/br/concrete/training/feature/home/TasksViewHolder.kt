package br.concrete.training.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.concrete.training.R
import br.concrete.training.data.model.Item2

/**
 * Created by eliete on 2/16/18.
 */
class TasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val task: TextView = itemView.findViewById(R.id.task)
    private val description: TextView = itemView.findViewById(R.id.description)

    fun bindView(item: Item2) {
        task.text = item.task
        description.text = item.description
    }

}