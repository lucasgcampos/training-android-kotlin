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
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by eliete on 2/16/18.
 */
class HomeActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val ITEM_EXTRAS = "ITEM_EXTRAS"
        const val itemActivityCode = 0x123
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
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
        recyclerView.adapter = homeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun bindViews() {
        recyclerView = recycler_view
        fab = add_item

        fab.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == itemActivityCode
                && resultCode == RESULT_OK
                && data?.hasExtra(ITEM_EXTRAS) == true) {
            val item = data.getParcelableExtra<Item2>(ITEM_EXTRAS)
            homeAdapter.includeItemList(item)
        }
    }
}