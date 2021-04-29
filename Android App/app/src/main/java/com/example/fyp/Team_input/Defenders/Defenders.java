package com.example.fyp.Team_input.Defenders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
            Log.d(TAG, "Gathered Player Data");
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Defenders.this,
                R.array.teams, android.R.layout.simple_spinner_item);

        def_one_team.setAdapter(adapter);
        def_two_team.setAdapter(adapter);
        def_three_team.setAdapter(adapter);
        def_four_team.setAdapter(adapter);
        def_five_team.setAdapter(adapter);




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
                    PopulateSpinner(position, def_two_name, 1);

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
                    PopulateSpinner(position, def_three_name, 2);

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
                    PopulateSpinner(position, def_four_name, 3);

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
                    PopulateSpinner(position, def_five_name, 4);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final int[] DEF_one_id = {0};


        def_one_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DEF_one_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(DEF_one_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        final int[] DEF_two_id = {0};


        def_two_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DEF_two_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(DEF_two_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] DEF_three_id = {0};


        def_three_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DEF_three_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(DEF_three_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] DEF_four_id = {0};


        def_four_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DEF_four_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(DEF_four_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] DEF_five_id = {0};


        def_five_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DEF_five_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(DEF_five_id[0]);
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
                if(DEF_Team_one.isEmpty() || DEF_Team_two.isEmpty() || DEF_Team_three.isEmpty() || DEF_Team_four.isEmpty() || DEF_Team_five.isEmpty()){
                    Toast.makeText(Defenders.this,"Please input Defenders",Toast.LENGTH_LONG).show();
                }
                else{
                    new AlertDialog.Builder(Defenders.this)
                            .setTitle("Goalkeepers Confirmation")
                            .setMessage("Are you sure this data is correct?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MT.addPlayer(DEF_Team_one.get(def_one_name.getSelectedItemPosition()), (float) def_one_price.getSelectedItem(), 2);
                                    Log.d(TAG, "INDEX 2");
                                    Log.d(TAG, "Price = " + (float) def_two_price.getSelectedItem());
                                    MT.addPlayer(DEF_Team_two.get(def_two_name.getSelectedItemPosition()), (float) def_two_price.getSelectedItem(), 3);
                                    Log.d(TAG, "INDEX 3");
                                    MT.addPlayer(DEF_Team_three.get(def_three_name.getSelectedItemPosition()), (float) def_three_price.getSelectedItem(), 4);
                                    Log.d(TAG, "INDEX 4");
                                    MT.addPlayer(DEF_Team_four.get(def_four_name.getSelectedItemPosition()), (float) def_four_price.getSelectedItem(), 5);
                                    MT.addPlayer(DEF_Team_five.get(def_five_name.getSelectedItemPosition()), (float) def_five_price.getSelectedItem(), 6);
                                    OpenMidfeilders();
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            }).show();


                }

            }
        });
    }

    private ArrayList<Player> createDefenders(ArrayList<Player> players) {
        ArrayList<Player> GK = new ArrayList<Player>();
        for(int i = 0; i <players.size();i++){
//            Log.d(TAG, "Adding Name = " + players.get(i).getName());
            if(players.get(i).getPosition() == 2){
                GK.add(players.get(i));
            }
        }
        return GK;
    }

    private void PopulateSpinner(int id, Spinner S, int playerNum){
        if(playerNum == 0){
            DEF_Name_one_team.clear();
            DEF_Team_one.clear();
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
            DEF_Name_two_team.clear();
            DEF_Team_two.clear();
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
            DEF_Name_three_team.clear();
            DEF_Team_three.clear();
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
            DEF_Name_four_team.clear();
            DEF_Team_four.clear();
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
            DEF_Name_five_team.clear();
            DEF_Team_five.clear();
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

    private void OpenMidfeilders() {
        Intent i = new Intent(this , Midfeilders.class);
        i.putExtra("ManagerTeam", MT);
        i.putExtra("GP", GP);
        startActivity(i);

    }
}