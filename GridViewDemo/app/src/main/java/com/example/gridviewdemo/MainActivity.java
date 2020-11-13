package com.example.gridviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
GridView gv;
int logo[]={R.drawable.c,R.drawable.cc,R.drawable.core,R.drawable.img1,R.drawable.img2,R.drawable.java,R.drawable.php,R.drawable.python};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv=(GridView)findViewById(R.id.grid);
        customAdapter adp=new customAdapter(MainActivity.this,logo);
        gv.setAdapter(adp);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override                                                     //position
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("img",logo[i]);
                startActivity(intent);
            }
        });
    }
}
