package com.example.fyp.Team_input.Defenders;

import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Entity.Player;
import com.example.fyp.Team_input.Goalkeepers.Goalkeeper;
import com.example.fyp.Team_input.Midfeilders.Midfeilders;
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

public class Defenders extends AppCompatActivity {

    private static final String TAG = "DEFENDERS ACTIVITY";


    private ArrayList<Player> Players, Defenders;
    private ArrayList<Float> AR = new ArrayList<Float>();

    //Player data storage.

    ArrayList<Player> DEF_Team_one = new ArrayList<Player>();
    ArrayList<String> DEF_Name_one_team = new ArrayList<String>();

    ArrayList<Player> DEF_Team_two = new ArrayList<Player>();
    ArrayList<String> DEF_Name_two_team = new ArrayList<String>();

    ArrayList<Player> DEF_Team_three = new ArrayList<Player>();
    ArrayList<String> DEF_Name_three_team = new ArrayList<String>();

    ArrayList<Player> DEF_Team_four = new ArrayList<Player>();
    ArrayList<String> DEF_Name_four_team = new ArrayList<String>();

    ArrayList<Player> DEF_Team_five = new ArrayList<Player>();
    ArrayList<String> DEF_Name_five_team = new ArrayList<String>();



    private Spinner def_one_team, def_one_name,def_one_price;
    private Spinner def_two_team,def_two_name,def_two_price;
    private Spinner def_three_team, def_three_name,def_three_price;
    private Spinner def_four_team,def_four_name,def_four_price;
    private Spinner def_five_team, def_five_name,def_five_price;
    private Button Confirm;


    private GatherPlayers GP;
    private ManagerTeam MT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defenders);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GP = (GatherPlayers) getIntent().getSerializableExtra("GP");
            MT = (ManagerTeam) getIntent().getSerializableExtra("ManagerTeam");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Players = GP.getPlayers();
        Defenders = createDefenders(Players);
        populateArrayList();

        Confirm = findViewById(R.id.DEF_enter_button);


        def_one_team = findViewById(R.id.DEF_Team_One);
        def_one_name = findViewById(R.id.DEF_Name_One);
        def_one_price = findViewById(R.id.DEF_SellPrice_One);

        def_two_team = findViewById(R.id.DEF_Team_Two);
        def_two_name = findViewById(R.id.DEF_Name_Two);
        def_two_price = findViewById(R.id.DEF_SellPrice_Two);

        def_three_team = findViewById(R.id.DEF_Team_Three);
        def_three_name = findViewById(R.id.DEF_Name_Three);
        def_three_price = findViewById(R.id.DEF_SellPrice_Three);

        def_four_team = findViewById(R.id.DEF_Team_Four);
        def_four_name = findViewById(R.id.DEF_Name_Four);
        def_four_price = findViewById(R.id.DEF_SellPrice_Four);

        def_five_team = findViewById(R.id.DEF_Team_Five);
        def_five_name = findViewById(R.id.DEF_Name_Five);
        def_five_price = findViewById(R.id.DEF_SellPrice_Five);

        ArrayAdapter<Float> adapterFloat = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, AR);
        adapterFloat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        def_one_price.setAdapter(adapterFloat);
        def_two_price.setAdapter(adapterFloat);
        def_three_price.setAdapter(adapterFloat);
        def_four_price.setAdapter(adapterFloat);
        def_five_price.setAdapter(adapterFloat);

        def_one_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)def_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, def_one_name, 0);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        def_two_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)def_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, def_two_name, 0);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        def_three_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)def_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, def_three_name, 0);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        def_four_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)def_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, def_four_name, 0);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        def_five_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)def_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, def_five_name, 0);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DEF_Team_one.isEmpty() || DEF_Team_two.isEmpty() || DEF_Team_three.isEmpty() || DEF_Team_four.isEmpty() || DEF_Team_five.isEmpty()){
                    Toast.makeText(Defenders.this,"Please input Defenders",Toast.LENGTH_LONG).show();
                }
                MT.addPlayer(GK_Team_one.get(GK_one_id[0]), (float)gk_one_price.getSelectedItem(), 0);
                MT.addPlayer(GK_Team_two.get(GK_two_id[0]),(float) gk_two_price.getSelectedItem(), 1);
                OpenDefenders();
            }
        });
    }

    private ArrayList<Player> createDefenders(ArrayList<Player> players) {
        ArrayList<Player> GK = new ArrayList<Player>();
        for(int i = 0; i <players.size();i++){
            Log.d(TAG, "Adding Name = " + players.get(i).getName());
            if(players.get(i).getPosition() == 2){
                GK.add(players.get(i));
            }
        }
        return GK;
    }

    private void PopulateSpinner(int id, Spinner S, int playerNum){
        if(playerNum == 0){
            for(int i =0; i<Defenders.size(); i++){
                if(Defenders.get(i).getTeam() == id){
                    DEF_Team_one.add(Defenders.get(i));
                    DEF_Name_one_team.add(Defenders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DEF_Name_one_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 1){
            for(int i =0; i<Defenders.size(); i++){
                if(Defenders.get(i).getTeam() == id){
                    DEF_Team_two.add(Defenders.get(i));
                    DEF_Name_two_team.add(Defenders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DEF_Name_two_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 2){
            for(int i =0; i<Defenders.size(); i++){
                if(Defenders.get(i).getTeam() == id){
                    DEF_Team_three.add(Defenders.get(i));
                    DEF_Name_three_team.add(Defenders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DEF_Name_three_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 3){
            for(int i =0; i<Defenders.size(); i++){
                if(Defenders.get(i).getTeam() == id){
                    DEF_Team_four.add(Defenders.get(i));
                    DEF_Name_four_team.add(Defenders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DEF_Name_four_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 4){
            for(int i =0; i<Defenders.size(); i++){
                if(Defenders.get(i).getTeam() == id){
                    DEF_Team_five.add(Defenders.get(i));
                    DEF_Name_five_team.add(Defenders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DEF_Name_five_team);
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