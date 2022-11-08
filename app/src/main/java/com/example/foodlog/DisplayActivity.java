package com.example.foodlog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private ImageAdapter image_adapter;
    private ProgressBar loading_circle;
    private List<Upload> upload_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_item);
//        recycler_view = findViewById(R.id.recycler_view);
//        recycler_view.setHasFixedSize(true);
//        recycler_view.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));
//        loading_circle = findViewById(R.id.progress_circle);
//        loading_circle.setVisibility(View.VISIBLE);
//
//        loading_circle.setVisibility(View.INVISIBLE);
//        ImageVie mImageView;
//
//        upload_data = new ArrayList<>();
//        image_adapter = new ImageAdapter(DisplayActivity.this, upload_data);
       // DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Upload upload = postSnapshot.getValue(Upload.class);
//                    upload_data.add(upload);
//                }
//
//
//                loading_circle.setVisibility(View.INVISIBLE);
//                recycler_view.setAdapter(image_adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(DisplayActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                loading_circle.setVisibility(View.INVISIBLE);
//            }
//        });
    }
}