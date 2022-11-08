package com.example.foodlog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    private static final int Image_request = 1;
    private Button choose_button;
    private Button upload_button;
    private Button button_camera;
    private TextView show_uploads;
    private EditText file_name;
    private ImageView image_view;
    private ProgressBar progress_bar;
    public static final int Camera_Action = 1;
    private Uri image_url;
    private StorageReference storage_ref;
    private DatabaseReference database_ref;
    private StorageTask upload_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        choose_button = findViewById(R.id.choose_button);
        upload_button = findViewById(R.id.button_upload);
        button_camera = findViewById(R.id.camerabtn);
        show_uploads = findViewById(R.id.show_uploads);
        file_name = findViewById(R.id.edit_text);
        image_view = findViewById(R.id.image_view);
        progress_bar = findViewById(R.id.progress_bar);
        storage_ref = FirebaseStorage.getInstance().getReference("uploads");
        database_ref = FirebaseDatabase.getInstance().getReference("uploads");

        choose_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (getIntent().resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, Camera_Action);
                } else {
                    Toast.makeText(ImageActivity.this, "App does'nt support", Toast.LENGTH_SHORT).show();
                }
            }
        });

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upload_task != null && upload_task.isInProgress()) {
                    Toast.makeText(ImageActivity.this, "Uploading.....", Toast.LENGTH_SHORT).show();
                } else {
                    //uploadFile();
                    Toast.makeText(ImageActivity.this, "Successful.....", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ImageActivity.this, LoadActivity.class);
                    startActivity(intent);

                }
            }
        });

        show_uploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_image();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Image_request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_request && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            image_url = data.getData();
            Picasso.get().load(image_url).into(image_view);

        }
    }

    private String File_extension(Uri url) {
        ContentResolver C_R = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(C_R.getType(url));
    }

    private void uploadFile() {
        if (image_url != null) {
            StorageReference fileReference = storage_ref.child(System.currentTimeMillis()
                    + "." + File_extension(image_url));
            System.out.println("herererere");
            upload_task = fileReference.putFile(image_url)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progress_bar.setProgress(0);
                                }
                            }, 600);

                            Toast.makeText(ImageActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask =
                                    taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri downloadUrl = urlTask.getResult();

                            Upload upload = new
                                    Upload(file_name.getText().toString().trim(),
                                    downloadUrl.toString());
                            System.out.println("uploaded image " + upload.getImageUrl());
//                            String uploadId = database_ref.push().getKey();
//                            database_ref.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ImageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progress_bar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(this, "Kindly select file", Toast.LENGTH_SHORT).show();
        }
    }

    private void open_image() {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }
}
