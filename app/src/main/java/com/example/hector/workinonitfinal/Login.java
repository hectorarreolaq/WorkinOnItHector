package com.example.hector.workinonitfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d("Usuario", "onAuthStateChanged:sign_in:"+user.getUid());
                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
                    intent.putExtra("usuario", user.getUid());
                    startActivity(intent);
                }else{
                    Log.d("Usuario", "onAuthStateChange:signed_out");
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
        mAuth.signOut();
    }

    /*
    public void changeActivityMenuPrincipal(View v){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }
    */

    public void logIn(View v){
        if (TextUtils.isEmpty(email.getText().toString())){
            Toast.makeText(this, "Please Fill Email", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(Login.this, "Email or Password not valid!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void changeActivitySignup(View v){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }
}
