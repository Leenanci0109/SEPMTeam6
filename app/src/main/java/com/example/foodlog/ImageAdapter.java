package com.example.foodlog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
   private Context my_context;
   private List<Upload> my_uploads;

   public ImageAdapter(Context context, List<Upload> uploads) {
       my_context = context;
       my_uploads = uploads;
   }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(my_context).inflate(R.layout.image_item, parent, false);
       return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
       Upload upload_recent = my_uploads.get(position);
       holder.name.setText(upload_recent.getName());

       Picasso.get()
               .load(upload_recent.getImageUrl())
               .fit()
               .centerInside()
               .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return my_uploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_view_name);
            image = itemView.findViewById(R.id.image_view_upload);
        }
    }
    }

