package com.example.fyp.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Entity.Player;
import com.example.fyp.LoginActivity;
import com.example.fyp.Team_input.Goalkeepers.Goalkeeper;
import com.example.fyp.Team_input.Midfeilders.Midfeilders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    private static String TAG = "Settings activity";
    private SettingsViewModel SettingsViewModel;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef, UserRef;
    private FirebaseAuth Auth;
    private FirebaseUser user;
    private String UID, FnameS, LnameS;

    private Button CE, LogOut, RemoveAccount;
    private TextView Email,Fname,Lname;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        UID = user.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Users");
        Email = findViewById(R.id.Email_Text_View);
        Fname = findViewById(R.id.Fname_Text_View);
        Lname = findViewById(R.id.Lname_Text_View);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FindData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Email.setText(user.getEmail());
        UserRef.child("Fname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    FnameS = String.valueOf(task.getResult().getValue());
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        Fname.setText(FnameS);
        UserRef.child("Lname").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    LnameS = String.valueOf(task.getResult().getValue());
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        Lname.setText(LnameS);
        CE = findViewById(R.id.ConfirmEmail);
        CE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SendUserResetEmail();
            }
        });

        if(!user.isEmailVerified()){
            CE.setEnabled(false);
        }
        else{
            Toast.makeText(this, "Please verify email", Toast.LENGTH_SHORT).show();
        }



        LogOut = findViewById(R.id.Log_Out_button);
        LogOut.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SignOut();
            }
        });

        RemoveAccount = findViewById(R.id.Remove_Account_Button);
        RemoveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteUser(user);
            }
        });
    }

        private void FindData (DataSnapshot dataSnapshot){
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                if (ds.child("UID").getValue().equals(UID)) {
                    UserRef = ds.getRef();
                    Log.d(TAG, "User Reference now equals data snapshot instance.");
                    break;
                } else {
                    Log.d(TAG, "User reference was not saved as ds reference");
                }
            }

        }

        private void SendUserResetEmail () {

            Auth.sendPasswordResetEmail(user.getEmail())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                            }
                        }
                    });
        }


        private void DeleteUser (FirebaseUser user){
            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User account deleted.");
                                myRef.child(UID).removeValue();
                                Intent intent = new Intent(Settings.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    });
        }

    private void SignOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }


}

