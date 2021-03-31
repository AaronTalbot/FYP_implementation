package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fyp.Entity.GlobalVariable;
import com.example.fyp.Entity.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference PlayerRef, GoalKeeperRef, DefenderRef, MidfielderRef, AttackerRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Button Login, Register;
    private EditText email,password;
    private static final String TAG = "Login Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        final GlobalVariable Instance = GlobalVariable.getInstance();
        ArrayList<Player> Players = Instance.getPlayers();




//        if(Players.isEmpty()){
//            Log.d(TAG, "Players empty");
//        }




        Register = findViewById(R.id.Registerlogin);
        Login = findViewById(R.id.login);

        email = findViewById(R.id.username);
        password = findViewById(R.id.password);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            OpenMainPage();
        }


        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SignIn();
            }
        });

    }




    public void SignIn(){
        mAuth.signInWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(password.getText()))
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            OpenMainPage();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            // ...
                        }

                        // ...
                    }
                });
    }
    public void OpenMainPage(){

        Intent i = new Intent(this,OpeningPage.class);
//        i.putExtra("Arraylist", Players);
        startActivity(i);
    }

}