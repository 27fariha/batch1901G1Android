package com.example.firebaselogin1901g1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
EditText etemail,etpass;
Button login;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail=findViewById(R.id.editText);
        etpass=findViewById(R.id.editText2);
        login=findViewById(R.id.button);

        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtemail=etemail.getText().toString();
                String txtpass=etpass.getText().toString();
                if(TextUtils.isEmpty(txtemail) || TextUtils.isEmpty(txtpass)){
                    Toast.makeText(loginActivity.this,"Empty",Toast.LENGTH_LONG).show();
                }
                else{
                    LoginUser(txtemail,txtpass);
                }
            }
        });

    }

    private void LoginUser(String txtemail, String txtpass) {
        auth.signInWithEmailAndPassword(txtemail, txtpass).addOnSuccessListener( new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(loginActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                startActivity(new Intent(loginActivity.this,DashboardActivity.class));
                finish();
            }
        });
    }
}
