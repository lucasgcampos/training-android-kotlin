package br.concrete.training.feature.item

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

import br.concrete.training.data.model.Item
import br.concrete.training.R
import br.concrete.training.feature.home.JHomeActivity

import br.concrete.training.feature.home.JHomeActivity.Companion.ITEM_EXTRAS

/**
 * @author Lucas Campos
 */
class JItemActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var task: TextInputEditText
    private lateinit var description: TextInputEditText

    private val formItem: Item
        get() = Item(
                task.text.toString(),
                if (description.text.toString().isEmpty()) "-" else description.text.toString()
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        setUpScreen()
    }

    private fun setUpScreen() {
        bindViews()

        fab.setOnClickListener {
            if (task.text.toString().isEmpty()) {
                AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("O campo 'tarefa' é obrigatório!")
                        .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                        .show()
            } else {
                setResult(Activity.RESULT_OK, Intent().putExtra(ITEM_EXTRAS, formItem))
                finish()
            }
        }
    }

    private fun bindViews() {
        fab = findViewById(R.id.add_item)
        task = findViewById(R.id.task)
        description = findViewById(R.id.description)
    }
}
