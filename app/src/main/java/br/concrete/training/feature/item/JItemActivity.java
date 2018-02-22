package br.concrete.training.feature.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import br.concrete.training.data.model.Item2;
import br.concrete.training.R;

import static br.concrete.training.feature.home.JHomeActivity.ITEM_EXTRAS;

/**
 * @author Lucas Campos
 */
public class JItemActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextInputEditText task;
    private TextInputEditText description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setUpScreen();
    }

    private void setUpScreen() {
        bindViews();

        fab.setOnClickListener(view -> {
            if (task.getText().toString().isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("Atenção")
                        .setMessage("O campo 'tarefa' é obrigatório!")
                        .setPositiveButton(android.R.string.ok, (dialog, i) -> dialog.dismiss())
                        .show();
            } else {
                setResult(RESULT_OK, new Intent().putExtra(ITEM_EXTRAS, getFormItem()));
                finish();
            }
        });
    }

    private void bindViews() {
        fab = findViewById(R.id.add_item);
        task = findViewById(R.id.task_item);
        description = findViewById(R.id.description_item);
    }

    private Item2 getFormItem() {
        return new Item2(
                task.getText().toString(),
                description.getText().toString().isEmpty() ? "-" : description.getText().toString()
        );
    }
}
