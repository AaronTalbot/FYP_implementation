package com.example.fyp.Team_input.Goalkeepers;

import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.GlobalVariable;
import com.example.fyp.Entity.Player;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;

import java.util.ArrayList;

public class Goalkeeper extends AppCompatActivity {
    private static final String TAG = "GOALKEEPER INPUT";

    private ArrayList<Player> Players, GoalKeepers;
    private ArrayList<Float> AR = new ArrayList<Float>();


    private Spinner gk_one_team,gk_one_name,gk_one_price;
    private Spinner gk_two_team,gk_two_name,gk_two_price;

    private GatherPlayers GV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalkeeper);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GV = (GatherPlayers) getIntent().getSerializableExtra("GP");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Players = GV.getPlayers();
        GoalKeepers = CreateGoalKeepers(Players);
        populateArrayList();



        gk_one_team = findViewById(R.id.GK_team_one);
        gk_one_name = findViewById(R.id.GK_name_one);
        gk_one_price = findViewById(R.id.GK_sell_price_one);

        gk_two_team = findViewById(R.id.GK_team_two);
        gk_two_name = findViewById(R.id.GK_name_two);
        gk_two_price = findViewById(R.id.GK_sell_price_two);


        ArrayAdapter<Float> adapterFloat = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, AR);
        adapterFloat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gk_one_price.setAdapter(adapterFloat);
        gk_two_price.setAdapter(adapterFloat);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Goalkeeper.this,
                R.array.teams, android.R.layout.simple_spinner_item);

        gk_one_team.setAdapter(adapter);

        gk_one_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)gk_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, gk_one_name, 1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        gk_two_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)gk_two_name.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, gk_one_name, 2);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private ArrayList<Player> CreateGoalKeepers(ArrayList<Player> players) {
        ArrayList<Player> GK = new ArrayList<Player>();
        for(int i = 0; i <players.size();i++){
            Log.d(TAG, "Adding Name = " + players.get(i).getName());
            if(players.get(i).getPosition() == 1){
                GK.add(players.get(i));
            }
        }
        return GK;
    }

    private void PopulateSpinner(int id, Spinner S, int playerNum){
        ArrayList<Player> GK_Team = new ArrayList<Player>();
        ArrayList<String> GK_Name_one_team = new ArrayList<String>();
        for(int i =0; i<GoalKeepers.size(); i++){
            if(GoalKeepers.get(i).getTeam() == id){
                GK_Team.add(GoalKeepers.get(i));
                GK_Name_one_team.add(GoalKeepers.get(i).getName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, GK_Name_one_team);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        S.setAdapter(adapter);

    }

    private void populateArrayList() {
        float f = 10.0f;
        for(int i = 0; i<151; i++){
            float num = i/f;
            AR.add(num);
        }
    }
}