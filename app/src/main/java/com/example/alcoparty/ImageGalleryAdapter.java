package com.example.alcoparty;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.ArrayList;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {
    ArrayList<Uri> arrayList;
    Context context;

    public ImageGalleryAdapter(Context cont, ArrayList<Uri> list) {
        context = cont;
        arrayList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                //.load(arrayList.get(position).getPath()) // Uri of the picture
             //   .load((Uri.parse("content://media/external/images/media/558268")))
                .load("https://avatars.mds.yandex.net/get-pdb/2062817/be9fb2f6-74e4-485d-8874-b9daabfd25b8/s1200")

                .placeholder(R.drawable.icons8_beer_bottle_96)
                .dontAnimate()
                .into(holder.image);
        //        .placeholder(R.drawable.icons8_beer_bottle_96)
        //        .dontAnimate()
       // holder.image.setImageURI(null);

       // holder.image.setImageURI(arrayList.get(position));
      //  holder.image.postInvalidate();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}
