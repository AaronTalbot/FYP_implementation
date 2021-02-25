package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference myRef, UserRef, InsuranceRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private static final String TAG = "Registration";

    private EditText Email1,Email2,Password1,Password2, Fname,Lname;
    private Button Register;


    private boolean PasswordBool;
    private boolean EmailSame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        Email1 = findViewById(R.id.editTextTextEmailAddress);
        Email2 = findViewById(R.id.editTextTextEmailAddressConfirm);

        Password1 = findViewById(R.id.editTextTextPassword2);
        Password2 = findViewById(R.id.editTextTextPassword3);

        Fname = findViewById(R.id.editTextTextPersonName);
        Lname = findViewById(R.id.editTextTextPersonName2);

        Register = findViewById(R.id.RegisterViewRegisterButton);
        if(user != null){
//            TODO add activity change
//            Intent i =
//            startActivity();
        }

        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CreateUser();
            }
        });


    }

    private void CreateUser(){
        String Email = String.valueOf(Email1.getText());
        String EmailConfirm = String.valueOf(Email2.getText());


        EmailSame = false;
        PasswordBool = false;

        if(Email.equals(EmailConfirm) & !Email.isEmpty()) {
            EmailSame = true;
        }

        String password = String.valueOf(Password1.getText());
        String pconfirm = String.valueOf(Password2.getText());

        if(password.equals(pconfirm) & password.length()>7){
            PasswordBool = true;
        }




        if(EmailSame & PasswordBool) {
            mAuth.createUserWithEmailAndPassword(Email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(RegistrationActivity.this,"Authentication Success.",Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }

    }
}