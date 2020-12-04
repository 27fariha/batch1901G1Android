package com.example.firebaserealtime1901g1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
EditText et;
Button btn;
ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.editTextTextPersonName);
        btn=findViewById(R.id.button);
        lv=findViewById(R.id.list);
        //1st method
       FirebaseDatabase.getInstance().getReference().child("Students").child("Name").setValue("Imad");

        //2nd method
        HashMap<String,Object> map=new HashMap<>();
        map.put("Name","Jaffar");
        map.put("Email","jaffar@aptech.com");

        FirebaseDatabase.getInstance().getReference().child("Login").child("Admin").updateChildren(map);

        HashMap<String,Object> map1=new HashMap<>();
        map1.put("Name","imad");
        map1.put("Email","imad@aptech.com");
        FirebaseDatabase.getInstance().getReference().child("Login").child("Users").updateChildren(map1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_lang=et.getText().toString();
                if(txt_lang.isEmpty()){
                    Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_LONG).show();
                }
                else{
                    FirebaseDatabase.getInstance().getReference().child("Languages").push().child("Name").setValue(txt_lang);
                    et.setText("");
                }
            }
        });

        ArrayList<String> arr=new ArrayList<>();
        ArrayAdapter adp=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adp);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Languages");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot dataSnapshot) {
                arr.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    arr.add(snap.getValue().toString());
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError databaseError) {

            }
        });
    }
}