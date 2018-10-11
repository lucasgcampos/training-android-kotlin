package br.concrete.training.feature.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.concrete.training.data.model.Item2;
import br.concrete.training.R;

import static br.concrete.training.feature.home.Api.*;

/**
 * @author Lucas Campos
 */

public class JHomeAdapter extends RecyclerView.Adapter<JHomeAdapter.ViewHolder> {

    private List<Item2> items = fetchDefaultItems();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    void addItem(Item2 item) {
        items.add(item);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView task;
        private TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task_item);
            description = itemView.findViewById(R.id.description_item);
        }

        private void bindView(Item2 item) {
            task.setText(item.getTask());
            description.setText(item.getDescription());
        }

    }
}

final class Api {
    static ArrayList<Item2> fetchDefaultItems() {
        ArrayList<Item2> items = new ArrayList<>();

        items.add(new Item2("Estudar Kotlin", "Estudar a estrutura básica da linguagem"));
        items.add(new Item2("Estudar Dagger2", "Estudar o conceito de injeção de dependência e depois entender como funciona o dagger2 "));

        return items;
    }
}
