package br.concrete.training.feature.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import br.concrete.training.R
import br.concrete.training.data.model.Item
import br.concrete.training.feature.item.JItemActivity

/**
 * @author Lucas Campos
 */

class JHomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var adapter: JHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpScreen()
    }

    private fun setUpScreen() {
        bindViews()

        adapter = JHomeAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivityForResult(
                    Intent(this, JItemActivity::class.java), itemActivityCode)
        }
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.add_item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == itemActivityCode
                && resultCode == Activity.RESULT_OK
                && data != null && data.hasExtra(ITEM_EXTRAS)) {
            val item = data.getParcelableExtra<Item>(ITEM_EXTRAS)
            adapter.addItem(item)
        }
    }

    companion object {
        const val ITEM_EXTRAS = "ITEM.EXTRAS"
        const val itemActivityCode = 0x123
    }
}