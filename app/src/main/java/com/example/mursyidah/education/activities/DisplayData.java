package com.example.mursyidah.education.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mursyidah.education.R;
import com.example.mursyidah.education.sql.DatabaseHelper;

/**
 * Created by mursyidah on 11/10/2017.
 */

public class DisplayData extends AppCompatActivity {

    private TextView textView2;
    private ImageView imageView2;
    DatabaseHelper mDatabaseHelper;
    private String selectedName;
    private int selectedID;




    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);
        textView2 = (TextView) findViewById(R.id.textView2);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        mDatabaseHelper = new DatabaseHelper(this);


        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");
        textView2.setText(selectedName);

        byte[] selectedImage = receivedIntent.getByteArrayExtra("image");
        final Bitmap bitmap = BitmapFactory.decodeByteArray(selectedImage, 0 ,selectedImage.length );
        imageView2.setImageBitmap(bitmap);


    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
