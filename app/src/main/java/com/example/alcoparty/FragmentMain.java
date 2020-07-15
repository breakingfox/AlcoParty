package com.example.alcoparty;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class FragmentMain extends Fragment {
    private static final String LOG_TAG = "myLogs";
    Button btnAlco;
    SQLiteDatabase db;
    ContentValues cv;
    Calendar calendar;
    TextView textAlco;
    int curMl;
    String curId;
    Cursor c;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        final DBAdapter DBHelper = new DBAdapter(getActivity());
        db = DBHelper.getWritableDatabase();
        //   db.execSQL("delete from main");


        calendar = Calendar.getInstance();
        // calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH + 1));
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Calendar dbCalendar = Calendar.getInstance();

        cv = new ContentValues();
      //   db.execSQL("DELETE FROM main");

       //   cv.put("date", "01.06.2021");
    //    cv.put("ml", "100");
       //  db.insert("main", null, cv);
        textAlco = v.findViewById(R.id.textAlco);
        boolean alreadyInTable = false;
        c = db.query("main", null, null, null, null, null, null);
        if (c.moveToFirst()) { // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int dateColIndex = c.getColumnIndex("date");
            int mlColIndex = c.getColumnIndex("ml");
            do { // получаем значения по номерам столбцов и пишем все в лог
                Log.d(
                        LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", date = " + c.getString(dateColIndex) +
                                ", alco = " + c.getString(mlColIndex)
                );
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    dbCalendar.setTime(Objects.requireNonNull(dateFormat.parse(c.getString(dateColIndex))));
                   // dbCalendar.set(Calendar.MONTH, dbCalendar.get(Calendar.MONTH));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.d(LOG_TAG, String.valueOf(calendar.getTimeInMillis()) + " " + String.valueOf(dbCalendar.getTimeInMillis()));
                if (calendar.getTimeInMillis() == dbCalendar.getTimeInMillis()) {
                    Log.d(LOG_TAG, "Ya it worked");
                    alreadyInTable = true;
                }
            }
            while (!alreadyInTable&&c.moveToNext() );
            if (!alreadyInTable) {
                Log.d(LOG_TAG, calendar.getTime().toString());
                Log.d(LOG_TAG, String.valueOf(calendar.getTimeInMillis()));
                int curMonth = calendar.get(Calendar.MONTH)+1;
                cv.put("date", calendar.get(Calendar.DAY_OF_MONTH)+"."+curMonth+"."+calendar.get(Calendar.YEAR));
                cv.put("ml", 0);
                db.insert("main", null, cv);
                curMl = 0;
                Log.d(LOG_TAG, "Made a new one");
                c.moveToLast();
            }
            curMl = Integer.parseInt(c.getString(mlColIndex));
            curId = c.getString(idColIndex);
            textAlco.setText(curMl + " ml");
            cv.clear();

        } else {
            int curMonth = calendar.get(Calendar.MONTH)+1;
            cv.put("date", calendar.get(Calendar.DAY_OF_MONTH)+"."+curMonth+"."+calendar.get(Calendar.YEAR));
            cv.put("ml", 0);
            db.insert("main", null, cv);
            textAlco.setText("0 ml");
        }
        c.close();
        db.close();
        btnAlco = (Button) v.findViewById(R.id.buttonAlco);
        btnAlco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = DBHelper.getWritableDatabase();
                curMl += 50;
                System.out.println(curMl);
                cv.put("ml", curMl);
                db.update("main", cv, "id= ? ", new String[]{curId});
                //  System.out.println(curId);

                // curMl = Integer.parseInt(c.getString(c.getColumnIndex("ml")));
                //  System.out.println(curMl);
                textAlco.setText(String.valueOf(curMl) + " ml");
                db.close();
            }
        });

        return v;
    }

    private void selectAll() throws ParseException {
        calendar = Calendar.getInstance();
        // calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH + 1));
        calendar.clear(Calendar.MILLISECOND);
        calendar.clear(Calendar.SECOND);

        calendar.clear(Calendar.MINUTE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Calendar dbCalendar = Calendar.getInstance();
        DBAdapter DBHelper = new DBAdapter(getActivity());
        db = DBHelper.getWritableDatabase();
        cv = new ContentValues();
        //  cv.put("date", "WORKED !st TIME!");
        //  cv.put("ml", "500");
        // db.insert("main", null, cv);

        Cursor c = db.query("main", null, null, null, null, null, null);
        if (c.moveToFirst()) { // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int dateColIndex = c.getColumnIndex("date");
            int mlColIndex = c.getColumnIndex("ml");
            do { // получаем значения по номерам столбцов и пишем все в лог
                Log.d(
                        LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", date = " + c.getString(dateColIndex) +
                                ", alco = " + c.getString(mlColIndex)
                );
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                dbCalendar.setTime(dateFormat.parse(c.getString(dateColIndex)));
                System.out.println("----------------------------------------");
                System.out.println(calendar.getTimeInMillis());
                System.out.println(calendar.getTime().toString());
                System.out.println(dbCalendar.getTimeInMillis());
                System.out.println(dbCalendar.getTime().toString());

                if (calendar.getTimeInMillis() == dbCalendar.getTimeInMillis())
                    System.out.println("YA it worked");
                else
                    System.out.println("Nope. Not this time");
                System.out.println("----------------------------------------");

                // переход на следующую строку
// а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else Log.d(LOG_TAG, "0 rows");
        c.close();
        db.close();

    }
}
