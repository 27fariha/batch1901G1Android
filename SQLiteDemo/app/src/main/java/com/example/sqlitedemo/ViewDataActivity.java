package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lv;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        lv=findViewById(R.id.list);
        Create();
        get();
    }

    public void get(){
        String query=" Select * from StudentData ";
        Cursor cr;

        list=new ArrayList<String>();
        cr=db.rawQuery(query,null);
        while(cr.moveToNext()){
            String name=cr.getString(0);
            String sub=cr.getString(1);
            list.add(name+"\n"+sub);
        }
        ArrayAdapter adp=new ArrayAdapter(ViewDataActivity.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adp);
    }

    public void Create(){
        db=openOrCreateDatabase("StudentDb",MODE_PRIVATE,null);
        db.execSQL("create table if not exists StudentData (name varchar , subject text) ");
                                                            //0                 1
    }
}
