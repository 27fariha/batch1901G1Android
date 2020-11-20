package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText etname,etsub;
Button ins,view,update,del;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Create();
        etname=findViewById(R.id.editname);
        etsub=findViewById(R.id.editsubject);

        ins=findViewById(R.id.btnins);
        view=findViewById(R.id.btnview);
        update=findViewById(R.id.btnupdate);
        del=findViewById(R.id.btndel);



        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("Insert into StudentData values ('"+etname.getText().toString()+"','"+etsub.getText().toString()+"') ");
                Toast.makeText(MainActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
                etname.setText("");
                etsub.setText("");
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ViewDataActivity.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL(" Update StudentData set subject='" +etsub.getText().toString()+"' where name ='"+etname.getText().toString() +"'");
                Toast.makeText(MainActivity.this,"Data Updated ",Toast.LENGTH_LONG).show();
                etname.setText("");
                etsub.setText("");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL(" Delete from StudentData where name ='"+etname.getText().toString() +"'");
                Toast.makeText(MainActivity.this,"Data Deleted ",Toast.LENGTH_LONG).show();
                etname.setText("");
                etsub.setText("");
            }
        });
    }

    public void Create(){
        db=openOrCreateDatabase("StudentDb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists StudentData (name varchar , subject text) ");

    }
}
