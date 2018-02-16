package br.concrete.training.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.concrete.training.data.model.Item2;
import br.concrete.training.R;
import br.concrete.training.feature.item.JItemActivity;

/**
 * @author Lucas Campos
 */

public class JHomeActivity extends AppCompatActivity {

    public static final String ITEM_EXTRAS = "ITEM.EXTRAS";
    private static final int itemActivityCode = 0x123;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private JHomeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpScreen();
    }

    private void setUpScreen() {
        bindViews();

        adapter = new JHomeAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(view ->
                startActivityForResult(
                        new Intent(this, JItemActivity.class), itemActivityCode)
        );
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.add_item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == itemActivityCode && resultCode == RESULT_OK && data != null && data.hasExtra(ITEM_EXTRAS)) {
            Item2 item = data.getParcelableExtra(ITEM_EXTRAS);
            adapter.addItem(item);
        }
    }
}