package com.example.fyp.Team_input.Goalkeepers;

import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Entity.Player;
import com.example.fyp.Team_input.Defenders.Defenders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

import java.util.ArrayList;

public class Goalkeeper extends AppCompatActivity {
    private static final String TAG = "GOALKEEPER INPUT";

    private ArrayList<Player> Players, GoalKeepers;
    private ArrayList<Float> AR = new ArrayList<Float>();

    ArrayList<Player> GK_Team_one = new ArrayList<Player>();
    ArrayList<String> GK_Name_one_team = new ArrayList<String>();

    ArrayList<Player> GK_Team_two = new ArrayList<Player>();
    ArrayList<String> GK_Name_two_team = new ArrayList<String>();


    private Spinner gk_one_team,gk_one_name,gk_one_price;
    private Spinner gk_two_team,gk_two_name,gk_two_price;
    private Button Confirm;


    private GatherPlayers GP;
    private ManagerTeam MT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goalkeeper);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GP = (GatherPlayers) getIntent().getSerializableExtra("GP");
            MT = (ManagerTeam) getIntent().getSerializableExtra("ManagerTeam");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Players = GP.getPlayers();
        GoalKeepers = CreateGoalKeepers(Players);
        populateArrayList();

        Confirm = findViewById(R.id.GoalKeeper_Enter_Button);


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
        gk_two_team.setAdapter(adapter);

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
                    PopulateSpinner(position, gk_one_name, 0);

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
                }
                else{
                    PopulateSpinner(position, gk_two_name, 1);
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
        final int[] GK_one_id = {0};

        gk_one_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GK_one_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        final int[] GK_two_id = {0};

        gk_one_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GK_two_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_two_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });




        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GK_Team_one.isEmpty() & GK_Team_two.isEmpty()){
                    Toast.makeText(Goalkeeper.this,"Please input goalkeepers",Toast.LENGTH_LONG).show();
                }
                MT.addPlayer(GK_Team_one.get(GK_one_id[0]), (float)gk_one_price.getSelectedItem(), 0);
                MT.addPlayer(GK_Team_two.get(GK_two_id[0]),(float) gk_two_price.getSelectedItem(), 1);
                OpenDefenders();
            }
        });

    }

    private void OpenDefenders() {
        Intent i = new Intent(this , Defenders.class);
        i.putExtra("ManagerTeam", MT);
        i.putExtra("GP", GP);
        startActivity(i);

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
        if(playerNum == 0){
            for(int i =0; i<GoalKeepers.size(); i++){
                if(GoalKeepers.get(i).getTeam() == id){
                    GK_Team_one.add(GoalKeepers.get(i));
                    GK_Name_one_team.add(GoalKeepers.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, GK_Name_one_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 1){
            for(int i =0; i<GoalKeepers.size(); i++){
                if(GoalKeepers.get(i).getTeam() == id){
                    GK_Team_two.add(GoalKeepers.get(i));
                    GK_Name_two_team.add(GoalKeepers.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, GK_Name_two_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }


    }

    private void populateArrayList() {
        float f = 10.0f;
        for(int i = 0; i<151; i++){
            float num = i/f;
            AR.add(num);
        }
    }
}