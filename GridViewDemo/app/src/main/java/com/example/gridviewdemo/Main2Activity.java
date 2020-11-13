package com.example.gridviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

public class Main2Activity extends AppCompatActivity {
ImageView iv;
Button imgsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        iv=(ImageView)findViewById(R.id.imageView2);
        imgsave=findViewById(R.id.imgsave);
        Intent intent=getIntent();
        iv.setImageResource(intent.getIntExtra("img",0));

        ActivityCompat.requestPermissions(Main2Activity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        File sdcard= Environment.getExternalStorageDirectory(); //get mobile environment
        final File directory=new File(sdcard.getAbsolutePath() + "/DownloadImage");
        directory.mkdir(); //create folder

        imgsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable d=(BitmapDrawable)iv.getDrawable(); //get image from drawable
                Bitmap bit =d.getBitmap();
                String filename=String.format("%d.png",System.currentTimeMillis()); // file name
                File outputfile=new File(directory,filename);
                Toast.makeText(Main2Activity.this,"Image saved",Toast.LENGTH_LONG).show();

                try{
                    FileOutputStream outputStream=new FileOutputStream(outputfile);
                    bit.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    outputStream.flush();
                    outputStream.close();



                    Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outputfile));
                    sendBroadcast(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}
