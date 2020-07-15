package com.example.alcoparty;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import static android.provider.MediaStore.*;

public class Highlight extends AppCompatActivity {
    SQLiteDatabase db;
    String id;
    String uri;
    Cursor c;
    TextView date, beer, vodka;
    RecyclerView recyclerImages;
    Button addPhoto;
    static final int GALLERY_REQUEST = 1;
    ContentValues cv;
    ArrayList<Uri> listUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight);
        date = findViewById(R.id.date);
        beer = findViewById(R.id.beer);
        vodka = findViewById(R.id.vodka);
        GridLayoutManager gridManager = new GridLayoutManager(this, 2);

        DBAdapter DBHelper = new DBAdapter(this);
        db = DBHelper.getWritableDatabase();
        id = getIntent().getStringExtra("id");
        c = db.rawQuery("select * from main where id ='" + id + "'", null);
        c.moveToFirst();
        date.setText(c.getString(c.getColumnIndex("date")));
        vodka.setText(c.getString(c.getColumnIndex("ml")));
        addPhoto = findViewById(R.id.buttonAddImage);
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });

        c = db.query("pictures", null, null, null, null, null, null);
        c = db.rawQuery("select * from pictures where id ='" + id + "'", null);
        listUri = new ArrayList<Uri>();
        c.moveToFirst();

        //content://media/external/images/media/566020
//        Log.d("myLogs","ID ="+id+"URI = "+Uri.parse(c.getString(1)));
        while (c.moveToNext()) {
            listUri.add(Uri.parse(c.getString(1)));
            Log.d("myLogs","ID = "+id+" URI = "+Uri.parse(c.getString(1)));
        }
        ImageGalleryAdapter imageGalleryAdapter = new ImageGalleryAdapter(this, listUri);
        recyclerImages = findViewById(R.id.images);
        recyclerImages.setLayoutManager(gridManager);
        recyclerImages.setHasFixedSize(true);
        recyclerImages.setAdapter(imageGalleryAdapter);
        //c.close();
     //   db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
  //     ImageView imagePhoto = (ImageView) findViewById(R.id.imageView);

        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    cv = new ContentValues();
                    cv.put("id", id);
                    cv.put("picture", selectedImage.toString());
                    db.insert("pictures", null, cv);
                  //  imagePhoto.setImageBitmap(bitmap);
                }
        }
    }
}
