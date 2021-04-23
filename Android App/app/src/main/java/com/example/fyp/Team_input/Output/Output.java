package com.example.fyp.Team_input.Output;

import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Team_input.Midfeilders.Midfeilders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fyp.R;

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


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}