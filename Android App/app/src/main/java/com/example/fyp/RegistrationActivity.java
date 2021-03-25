package com.example.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Entity.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class RegistrationActivity extends AppCompatActivity {
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference myRef, UserRef, InsuranceRef;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference UserRef;
    private static final String TAG = "Registration";

    private EditText Email1,Email2,Password1,Password2, Fname,Lname;
    private Button Register;


    private boolean PasswordBool;
    private boolean EmailSame, Namebool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        mFirebaseDatabase = FirebaseDatabase.getInstance("https://final-year-project-dd795-default-rtdb.europe-west1.firebasedatabase.app/");
        UserRef = mFirebaseDatabase.getReference("Users");



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
        Namebool = false;

        if(Email.equals(EmailConfirm) & !Email.isEmpty()) {
            EmailSame = true;
        }

        String password = String.valueOf(Password1.getText());
        String pconfirm = String.valueOf(Password2.getText());

        if(password.equals(pconfirm) & password.length()>7){
            PasswordBool = true;
        }

        if(!Fname.getText().toString().isEmpty() & !Lname.getText().toString().isEmpty()){
            Namebool = true;
        }

        if(EmailSame & PasswordBool & Namebool) {
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
            User u = new User();
            u.setEmail(Email);
            u.setFname(Fname.getText().toString());
            u.setLname(Lname.getText().toString());
            u.setUID(user.getUid());

            DatabaseReference newUserRef = UserRef.push();

            newUserRef.child("Fname").setValue(u.getFname());
            newUserRef.child("Lname").setValue(u.getLname());
            newUserRef.child("Email").setValue(u.getEmail());
            newUserRef.child("UID").setValue(u.getUID());
            OpenMainPage();


        }
        else{
            if(!Namebool){
                Fname.setError("Name cannot be empty");
                Lname.setError("Name cannot be empty");
                Toast.makeText(RegistrationActivity.this,"Details incorrect",Toast.LENGTH_SHORT).show();
            }
            else
                if(!PasswordBool){
                    Password2.setError("Passwords must match");
                    Toast.makeText(RegistrationActivity.this,"Details incorrect",Toast.LENGTH_SHORT).show();
                }
            else
                if(!EmailSame){
                    Email2.setError("Emails must match");
                    Toast.makeText(RegistrationActivity.this,"Details incorrect",Toast.LENGTH_SHORT).show();
                }

        }

    }

    public void OpenMainPage(){
        Intent i = new Intent(this,OpeningPage.class);
        startActivity(i);
    }
}