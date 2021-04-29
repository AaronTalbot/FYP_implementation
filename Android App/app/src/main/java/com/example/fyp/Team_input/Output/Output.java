package com.example.fyp.Team_input.Output;

import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.AboutUs;
import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.LoginActivity;
import com.example.fyp.OpeningPage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;
import com.google.firebase.auth.FirebaseAuth;

public class Output extends AppCompatActivity {
    private static final String TAG = "OUTPUT ACTIVITY";


    TextView Attackers, Midfielders, Defenders;

    private GatherPlayers GP;
    private ManagerTeam MT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GP = (GatherPlayers) getIntent().getSerializableExtra("GP");
            MT = (ManagerTeam) getIntent().getSerializableExtra("ManagerTeam");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Defenders = findViewById(R.id.Defenders_Output);
        Midfielders = findViewById(R.id.Midfeilders_Output);
        Attackers = findViewById(R.id.Attackers_Output);

        Defenders.setText(MT.Defenders());
        Midfielders.setText(MT.Midfeilders());
        Attackers.setText(MT.Attackers());


        FloatingActionButton fab = findViewById(R.id.ReturnFAB);
        registerForContextMenu(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "Return Home");
        menu.add(0, v.getId(), 0, "Sign Out");
        menu.add(0, v.getId(), 0, "About Us");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle() == "Return Home"){
            RetunrHome();
        }
        else if(item.getTitle() == "Sign Out"){
            SignOut();
        }
        else if(item.getTitle() == "About Us"){
            AboutUs();
        }
        else{
            return false;
        }
        return true;
    }

    private void AboutUs() {
        Intent intent = new Intent(getApplicationContext(), AboutUs.class);
        startActivity(intent);
    }

    private void SignOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    private void RetunrHome() {
        Intent intent = new Intent(getApplicationContext(), OpeningPage.class);
        startActivity(intent);
    }
}