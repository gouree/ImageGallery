package com.example.gouree.imagegallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //set a static variable selected_picture
    private static final int SELECTED_PICTURE =1;
    ImageView iv;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create imageview and button variables
        iv= (ImageView)findViewById(R.id.imageView);
        Button button= (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent();

                // Show only images, no videos or anything else
                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),SELECTED_PICTURE);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECTED_PICTURE  && resultCode == RESULT_OK  && data != null && data.getData() != null)
        {
            Uri uri = data.getData();

            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                iv.setImageBitmap(bitmap);
            }catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }
}
