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

/**
 * Created by eliete on 2/15/18.
 */
class ItemActivity : AppCompatActivity(), View.OnClickListener {

    private var fab: FloatingActionButton? = null
    private var task: TextInputEditText? = null
    private var description: TextInputEditText? = null

    override fun onClick(p0: View?) {
        if (getItem() != null) {
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
        fab = findViewById(R.id.add_item)
        task = this.findViewById(R.id.task)
        description = findViewById(R.id.description)

        fab?.setOnClickListener(this)
    }

    fun getItem(): Item2? {
        val taskItem = task?.text.toString()

        return if (validateTaskItem(taskItem)) {
            Item2(taskItem, getDescriptionItem())
        } else {
            null
        }
    }

    fun validateTaskItem(task : String): Boolean {
        return !task.isEmpty()
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