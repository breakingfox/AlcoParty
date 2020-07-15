package com.example.alcoparty;

import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class FragmentTable extends Fragment {
    ArrayList<AlcoDay> list;
    SQLiteDatabase db;
    Cursor c;
    RecyclerView table;
    TableAdapter tableAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_table, container, false);

        DBAdapter DBHelper = new DBAdapter(getActivity());
        db = DBHelper.getWritableDatabase();
        table = v.findViewById(R.id.table);
        list = new ArrayList<AlcoDay>();
        c = db.query("main", null, null, null, null, null, null);
        LinearLayoutManager manager = new LinearLayoutManager(v.getContext());
        table.setLayoutManager(manager);
        table.setHasFixedSize(true);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int dateColIndex = c.getColumnIndex("date");
            int mlColIndex = c.getColumnIndex("ml");
            do {
                Log.d(
                        "myLogs",
                        "ID = " + c.getInt(idColIndex) +
                                ", date = " + c.getString(dateColIndex) +
                                ", alco = " + c.getString(mlColIndex)
                );
                AlcoDay item = new AlcoDay();
                if (c.getInt(mlColIndex) > 0) {
                    item.setId(c.getInt(idColIndex));
                    item.setDate(c.getString(dateColIndex));
                    item.setMl(c.getInt(mlColIndex));
                    list.add(item);
                }
            }
            while (c.moveToNext());
            tableAdapter = new TableAdapter(v.getContext(), list);
            table.setAdapter(tableAdapter);
        }

        // c.close();
        c = db.query("pictures", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            Log.d(
                    "myLogs",
                    "ID = " + c.getString(0) +
                            ", URI = " + c.getString(1));
        }
        while (c.moveToNext()) ;
        db.close();
        return v;
    }
}
