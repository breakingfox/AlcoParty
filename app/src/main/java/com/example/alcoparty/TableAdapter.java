package com.example.alcoparty;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.RowViewHolder> {
    ArrayList<AlcoDay> alcoList;
    Context context;
    int id;

    public TableAdapter(Context cont, ArrayList<AlcoDay> list) {
        context = cont;
        alcoList = list;
    }

    @NonNull
    @Override
    public RowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_table, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TableAdapter.RowViewHolder holder, final int position) {
        int rowPos = holder.getAdapterPosition();
        Log.d("myLogs", "Position of table " + rowPos);
        System.out.println("WE ARE HERE");
        if (rowPos == 0) {
            holder.id.setText("ID");
            holder.date.setText("DATE");
            holder.ml.setText("ML");
        } else {
            holder.id.setText(String.valueOf(alcoList.get(position - 1).getId()));
            holder.date.setText(alcoList.get(position - 1).getDate());
            holder.ml.setText(String.valueOf(alcoList.get(position - 1).getMl()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    Intent highlight = new Intent(context, Highlight.class);
                    highlight.putExtra("id", String.valueOf(alcoList.get(position - 1).getId()));
                    context.startActivity(highlight);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return alcoList.size() + 1;
    }

    class RowViewHolder extends RecyclerView.ViewHolder {
        TextView id, date, ml;

        public RowViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            ml = (TextView) itemView.findViewById(R.id.ml);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
