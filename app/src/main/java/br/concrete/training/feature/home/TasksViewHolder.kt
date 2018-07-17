package br.concrete.training.feature.home

import android.support.v7.widget.RecyclerView
import android.view.View
import br.concrete.training.data.model.Item2
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home.*

/**
 * Created by eliete on 2/16/18.
 */
class TasksViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindView(item: Item2) {
        task_item.text = item.task
        description_item.text = item.description
    }

}