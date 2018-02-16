package br.concrete.training.feature.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import br.concrete.training.R
import br.concrete.training.data.model.Item2
import br.concrete.training.feature.item.ItemActivity

/**
 * Created by eliete on 2/16/18.
 */
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val ITEM_EXTRAS = "ITEM_EXTRAS"
        val itemActivityCode = 0x123
    }

    private var recyclerView: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private lateinit var homeAdapter: HomeAdapter

    override fun onClick(p0: View?) {
        startActivityForResult(Intent(this, ItemActivity::class.java), itemActivityCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bindViews()
        initTaskList()
    }

    private fun initTaskList() {
        homeAdapter = HomeAdapter()
        recyclerView?.adapter = homeAdapter
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.add_item)

        fab?.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == itemActivityCode
                && resultCode == RESULT_OK
                && data != null
                && data.hasExtra(ITEM_EXTRAS)) {
            val item = data.getParcelableExtra<Item2>(ITEM_EXTRAS)
            homeAdapter.incluiItemNaLista(item)
        }
    }
}