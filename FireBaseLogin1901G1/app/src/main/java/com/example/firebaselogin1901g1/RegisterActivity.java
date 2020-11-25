package com.example.firebaselogin1901g1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText etemail,etpass;
    Button reg;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etemail=findViewById(R.id.editText);
        etpass=findViewById(R.id.editText2);
        reg=findViewById(R.id.button);

        auth=FirebaseAuth.getInstance();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email=etemail.getText().toString();
                String txt_pass=etpass.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)){
                    Toast.makeText(RegisterActivity.this,"Credential Empty",Toast.LENGTH_LONG).show();
                }
                else if(txt_pass.length() < 6){
                    Toast.makeText(RegisterActivity.this,"Password Length Too Short",Toast.LENGTH_LONG).show();
                }
                else{
                    UserRegister(txt_email,txt_pass);
                }
            }
        });
    }

    private void UserRegister(String txt_email, String txt_pass) {
        auth.createUserWithEmailAndPassword(txt_email, txt_pass).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
