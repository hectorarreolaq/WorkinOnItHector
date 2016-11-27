package com.example.hector.workinonitfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("Users");

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

    }

    public void changeActivityMenuPrincipal(View v){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    public void addUser(View v) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = task.getResult().getUser();
                        Usuario usuario = new Usuario(name.getText().toString(),email.getText().toString(), "");
                        dbRef.child(user.getUid()).setValue(usuario);
                        if (!task.isSuccessful()) {
                            Toast.makeText(Signup.this, "No Se Registro",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Toast.makeText(this, "Usuario Creado", Toast.LENGTH_LONG).cancel();

        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);

        //finish();

    }
}
