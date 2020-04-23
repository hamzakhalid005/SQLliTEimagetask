package com.example.sqllietask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText nameET;
    private ImageView image;
    private static final int pick_image_request = 100;
    private Uri imagefilepate;
    private Bitmap imgaetostore;
     DatabaseHandler objDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            nameET = findViewById(R.id.nameET);
            image = findViewById(R.id.image);
            objDatabaseHandler = new DatabaseHandler(this);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void choseimage(View objectView) {

        try {
            Intent objectintent = new Intent();
            objectintent.setType("image/*");
            objectintent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectintent, pick_image_request);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pick_image_request && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagefilepate = data.getData();
            try {
                imgaetostore = MediaStore.Images.Media.getBitmap(getContentResolver(), imagefilepate);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setImageBitmap(imgaetostore);
        }
    }

    public void storeimage(View view) {

        try {
            if (!nameET.getText().toString().isEmpty() && image.getDrawable() != null && imgaetostore != null) {
                objDatabaseHandler.storeimage(new ModelClass(nameET.getText().toString()), imgaetostore);
            } else {
                Toast.makeText(this, "select image name and image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}