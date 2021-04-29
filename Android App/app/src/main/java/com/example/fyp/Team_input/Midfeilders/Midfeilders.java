package com.example.fyp.Team_input.Midfeilders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Entity.Player;
import com.example.fyp.Team_input.Attackers.Attackers;
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

public class Midfeilders extends AppCompatActivity {
    private static final String TAG = "MIDFEILDERS ACTIVITY";


    private ArrayList<Player> Players, Midfeilders;
    private ArrayList<Float> AR = new ArrayList<Float>();

    //Player data storage.

    ArrayList<Player> MID_Team_one = new ArrayList<Player>();
    ArrayList<String> MID_Name_one_team = new ArrayList<String>();

    ArrayList<Player> MID_Team_two = new ArrayList<Player>();
    ArrayList<String> MID_Name_two_team = new ArrayList<String>();

    ArrayList<Player> MID_Team_three = new ArrayList<Player>();
    ArrayList<String> MID_Name_three_team = new ArrayList<String>();

    ArrayList<Player> MID_Team_four = new ArrayList<Player>();
    ArrayList<String> MID_Name_four_team = new ArrayList<String>();

    ArrayList<Player> MID_Team_five = new ArrayList<Player>();
    ArrayList<String> MID_Name_five_team = new ArrayList<String>();





    private Spinner MID_one_team, MID_one_name,MID_one_price;
    private Spinner MID_two_team,MID_two_name,MID_two_price;
    private Spinner MID_three_team, MID_three_name,MID_three_price;
    private Spinner MID_four_team,MID_four_name,MID_four_price;
    private Spinner MID_five_team, MID_five_name,MID_five_price;
    private Button Confirm;


    private GatherPlayers GP;
    private ManagerTeam MT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midfeilders);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GP = (GatherPlayers) getIntent().getSerializableExtra("GP");
            MT = (ManagerTeam) getIntent().getSerializableExtra("ManagerTeam");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Players = GP.getPlayers();
        Midfeilders = createMidfielders(Players);
        populateArrayList();

        Confirm = findViewById(R.id.MID_enter_button);


        MID_one_team = findViewById(R.id.MID_Team_One);
        MID_one_name = findViewById(R.id.MID_Name_One);
        MID_one_price = findViewById(R.id.MID_SellPrice_One);

        MID_two_team = findViewById(R.id.MID_Team_Two);
        MID_two_name = findViewById(R.id.MID_Name_Two);
        MID_two_price = findViewById(R.id.MID_SellPrice_Two);

        MID_three_team = findViewById(R.id.MID_Team_Three);
        MID_three_name = findViewById(R.id.MID_Name_Three);
        MID_three_price = findViewById(R.id.MID_SellPrice_Three);

        MID_four_team = findViewById(R.id.MID_Team_Four);
        MID_four_name = findViewById(R.id.MID_Name_Four);
        MID_four_price = findViewById(R.id.MID_SellPrice_Four);

        MID_five_team = findViewById(R.id.MID_Team_Five);
        MID_five_name = findViewById(R.id.MID_Name_Five);
        MID_five_price = findViewById(R.id.MID_SellPrice_Five);

        ArrayAdapter<Float> adapterFloat = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, AR);
        adapterFloat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        MID_one_price.setAdapter(adapterFloat);
        MID_two_price.setAdapter(adapterFloat);
        MID_three_price.setAdapter(adapterFloat);
        MID_four_price.setAdapter(adapterFloat);
        MID_five_price.setAdapter(adapterFloat);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Midfeilders.this,
                R.array.teams, android.R.layout.simple_spinner_item);

        MID_one_team.setAdapter(adapter);
        MID_two_team.setAdapter(adapter);
        MID_three_team.setAdapter(adapter);
        MID_four_team.setAdapter(adapter);
        MID_five_team.setAdapter(adapter);



        final int[] GK_one_id = {0};

        MID_one_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)MID_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, MID_one_name, 0);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MID_two_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)MID_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, MID_two_name, 1);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MID_three_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)MID_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, MID_three_name, 2);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MID_four_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)MID_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, MID_four_name, 3);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MID_five_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)MID_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, MID_five_name, 4);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final int[] MID_one_id = {0};


        MID_one_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MID_one_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(MID_one_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        final int[] MID_two_id = {0};


        MID_two_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MID_two_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(MID_two_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] MID_three_id = {0};


        MID_three_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MID_three_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(MID_three_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] MID_four_id = {0};


        MID_four_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MID_four_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(MID_four_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] MID_five_id = {0};


        MID_five_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MID_five_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(MID_five_id[0]);
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
                if(MID_Team_one.isEmpty() || MID_Team_two.isEmpty() || MID_Team_three.isEmpty() || MID_Team_four.isEmpty() || MID_Team_five.isEmpty()){
                    Toast.makeText(Midfeilders.this,"Please input MIDenders",Toast.LENGTH_LONG).show();
                }
                else{
                    new AlertDialog.Builder(Midfeilders.this)
                            .setTitle("Midfielders Confirmation")
                            .setMessage("Are you sure this data is correct?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MT.addPlayer(MID_Team_one.get(MID_one_id[0]), (float)MID_one_price.getSelectedItem(), 7);
                                    MT.addPlayer(MID_Team_two.get(MID_two_id[0]), (float)MID_two_price.getSelectedItem(), 8);
                                    MT.addPlayer(MID_Team_three.get(MID_three_id[0]), (float)MID_three_price.getSelectedItem(), 9);
                                    MT.addPlayer(MID_Team_four.get(MID_four_id[0]), (float)MID_four_price.getSelectedItem(), 10);
                                    MT.addPlayer(MID_Team_five.get(MID_five_id[0]), (float)MID_five_price.getSelectedItem(), 11);


                                    OpenAttackers();
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

    private void OpenAttackers() {
        Intent i = new Intent(this , Attackers.class);
        i.putExtra("ManagerTeam", MT);
        i.putExtra("GP", GP);
        startActivity(i);

    }

    private ArrayList<Player> createMidfielders(ArrayList<Player> players) {
        ArrayList<Player> GK = new ArrayList<Player>();
        for(int i = 0; i <players.size();i++){
            Log.d(TAG, "Adding Name = " + players.get(i).getName());
            if(players.get(i).getPosition() == 3){
                GK.add(players.get(i));
            }
        }
        return GK;
    }

    private void populateArrayList() {

        float f = 10.0f;
        for(int i = 0; i<151; i++){
            float num = i/f;
            AR.add(num);
        }
    }

    private void PopulateSpinner(int id, Spinner S, int playerNum){
        if(playerNum == 0){
            MID_Name_one_team.clear();
            MID_Team_one.clear();
            for(int i =0; i<Midfeilders.size(); i++){
                if(Midfeilders.get(i).getTeam() == id){
                    MID_Team_one.add(Midfeilders.get(i));
                    MID_Name_one_team.add(Midfeilders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MID_Name_one_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 1){
            MID_Name_two_team.clear();
            MID_Team_two.clear();
            for(int i =0; i<Midfeilders.size(); i++){
                if(Midfeilders.get(i).getTeam() == id){
                    MID_Team_two.add(Midfeilders.get(i));
                    MID_Name_two_team.add(Midfeilders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MID_Name_two_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 2){
            MID_Name_three_team.clear();
            MID_Team_three.clear();
            for(int i =0; i<Midfeilders.size(); i++){
                if(Midfeilders.get(i).getTeam() == id){
                    MID_Team_three.add(Midfeilders.get(i));
                    MID_Name_three_team.add(Midfeilders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MID_Name_three_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 3){
            MID_Name_four_team.clear();
            MID_Team_four.clear();
            for(int i =0; i<Midfeilders.size(); i++){
                if(Midfeilders.get(i).getTeam() == id){
                    MID_Team_four.add(Midfeilders.get(i));
                    MID_Name_four_team.add(Midfeilders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MID_Name_four_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }
        else if(playerNum == 4){
            MID_Name_five_team.clear();
            MID_Team_five.clear();
            for(int i =0; i<Midfeilders.size(); i++){
                if(Midfeilders.get(i).getTeam() == id){
                    MID_Team_five.add(Midfeilders.get(i));
                    MID_Name_five_team.add(Midfeilders.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, MID_Name_five_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        }

    }



}