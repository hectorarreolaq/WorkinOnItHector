package com.example.hector.workinonitfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

    }

    public void changeActivityMenuPrincipal(View v){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    public void changeActivitySignup(View v){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
