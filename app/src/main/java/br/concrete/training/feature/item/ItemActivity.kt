package br.concrete.training.feature.item

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.concrete.training.R
import br.concrete.training.data.model.Item2
import br.concrete.training.feature.home.HomeActivity
import kotlinx.android.synthetic.main.activity_item.*

/**
 * Created by eliete on 2/15/18.
 */
class ItemActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var fab: FloatingActionButton
    private lateinit var task: TextInputEditText
    private lateinit var description: TextInputEditText

    override fun onClick(p0: View?) {
        if (validateTaskItem(getItem())) {
            setResult(RESULT_OK, Intent().putExtra(HomeActivity.ITEM_EXTRAS, getItem()))
            finish()
        } else {
            fireDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        bindViews()
    }

    fun bindViews() {
        fab = add_item
        task = task_item
        description = description_item

        fab.setOnClickListener(this)
    }

    private fun getItem() : Item2 {
        val taskItem = task?.text.toString()
        return Item2(taskItem, getDescriptionItem())
    }

    fun validateTaskItem(item : Item2): Boolean {
        return !item.task.isEmpty()
    }

    fun getDescriptionItem() : String {
        return if (!this.description?.text?.isEmpty()!!) {
            description?.text.toString()
        } else {
            "-"
        }
    }

    fun fireDialog() {
        AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("O campo task é obrigatório!")
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .show()
    }
}