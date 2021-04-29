package com.example.fyp.Team_input.Attackers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.ManagerTeam;
import com.example.fyp.Entity.Player;
import com.example.fyp.Team_input.Attackers.Attackers;
import com.example.fyp.Team_input.Defenders.Defenders;
import com.example.fyp.Team_input.Midfeilders.Midfeilders;
import com.example.fyp.Team_input.Output.Output;
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

public class Attackers extends AppCompatActivity {

    private static final String TAG = "Attackers ACTIVITY";


    private ArrayList<Player> Players, Attackers;
    private ArrayList<Float> AR = new ArrayList<Float>();

    //Player data storage.

    ArrayList<Player> ATT_Team_one = new ArrayList<Player>();
    ArrayList<String> ATT_Name_one_team = new ArrayList<String>();

    ArrayList<Player> ATT_Team_two = new ArrayList<Player>();
    ArrayList<String> ATT_Name_two_team = new ArrayList<String>();

    ArrayList<Player> ATT_Team_three = new ArrayList<Player>();
    ArrayList<String> ATT_Name_three_team = new ArrayList<String>();


    private Spinner ATT_one_team, ATT_one_name,ATT_one_price;
    private Spinner ATT_two_team,ATT_two_name,ATT_two_price;
    private Spinner ATT_three_team, ATT_three_name,ATT_three_price;
    private Button Confirm;

    private GatherPlayers GP;
    private ManagerTeam MT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attackers);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            GP = (GatherPlayers) getIntent().getSerializableExtra("GP");
            MT = (ManagerTeam) getIntent().getSerializableExtra("ManagerTeam");
            Log.d(TAG, "GATHER PLAYERS PASSED");
        }

        Players = GP.getPlayers();
        Attackers = createAttackers(Players);
        populateArrayList();

        Confirm = findViewById(R.id.ATT_Enter_Button);


        ATT_one_team = findViewById(R.id.ATT_Team_One);
        ATT_one_name = findViewById(R.id.ATT_Name_One);
        ATT_one_price = findViewById(R.id.ATT_Sell_Price_One);

        ATT_two_team = findViewById(R.id.ATT_Team_Two);
        ATT_two_name = findViewById(R.id.ATT_Name_Two);
        ATT_two_price = findViewById(R.id.ATT_Sell_Price_Two);

        ATT_three_team = findViewById(R.id.ATT_Team_Three);
        ATT_three_name = findViewById(R.id.ATT_Name_Three);
        ATT_three_price = findViewById(R.id.ATT_Sell_Price_Three);

        ArrayAdapter<Float> adapterFloat = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, AR);
        adapterFloat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ATT_one_price.setAdapter(adapterFloat);
        ATT_two_price.setAdapter(adapterFloat);
        ATT_three_price.setAdapter(adapterFloat);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(com.example.fyp.Team_input.Attackers.Attackers.this,
                R.array.teams, android.R.layout.simple_spinner_item);

        ATT_one_team.setAdapter(adapter);
        ATT_two_team.setAdapter(adapter);
        ATT_three_team.setAdapter(adapter);

        final int[] GK_one_id = {0};

        ATT_one_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)ATT_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, ATT_one_name, 0);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ATT_two_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)ATT_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, ATT_two_name, 1);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ATT_three_team.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int GK_one_id = parent.getSelectedItemPosition();
                String id2 = Integer.toString(GK_one_id);
                Log.d(TAG, "position = " + id2);
                if (position == 0) {
                    // Notify the selected item text
                    Toast.makeText(getApplicationContext(), "Please select a team", Toast.LENGTH_SHORT).show();
                    ((TextView)ATT_one_team.getSelectedView()).setError("None Selected");;
                }
                else{
                    PopulateSpinner(position, ATT_three_name, 2);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final int[] ATT_one_id = {0};


        ATT_one_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ATT_one_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(ATT_one_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });

        final int[] ATT_two_id = {0};


        ATT_two_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ATT_two_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(ATT_two_id[0]);
                Log.d(TAG, "position = " + id2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "position = not selected");
            }
        });
        final int[] ATT_three_id = {0};


        ATT_three_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ATT_three_id[0] = parent.getSelectedItemPosition();
                String id2 = Integer.toString(ATT_three_id[0]);
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
                if(ATT_Team_one.isEmpty() || ATT_Team_two.isEmpty() || ATT_Team_three.isEmpty()){
                    Toast.makeText(Attackers.this,"Please input Attackers",Toast.LENGTH_LONG).show();
                }
                else{
                    new AlertDialog.Builder(Attackers.this)
                            .setTitle("Goalkeepers Confirmation")
                            .setMessage("Are you sure this data is correct?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MT.addPlayer(ATT_Team_one.get(ATT_one_id[0]), (float)ATT_one_price.getSelectedItem(), 12);
                                    MT.addPlayer(ATT_Team_two.get(ATT_two_id[0]), (float)ATT_two_price.getSelectedItem(), 13);
                                    MT.addPlayer(ATT_Team_three.get(ATT_three_id[0]), (float)ATT_three_price.getSelectedItem(), 14);
                                    OpenOutput();
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

    private ArrayList<Player> createAttackers(ArrayList<Player> players) {
        ArrayList<Player> GK = new ArrayList<Player>();
        for(int i = 0; i <players.size();i++){
            Log.d(TAG, "Adding Name = " + players.get(i).getName());
            if(players.get(i).getPosition() == 4){
                GK.add(players.get(i));
            }
        }
        return GK;
    }
    private void PopulateSpinner(int id, Spinner S, int playerNum) {
        if (playerNum == 0) {
            ATT_Name_one_team.clear();
            ATT_Team_one.clear();
            for (int i = 0; i < Attackers.size(); i++) {
                if (Attackers.get(i).getTeam() == id) {
                    ATT_Team_one.add(Attackers.get(i));
                    ATT_Name_one_team.add(Attackers.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ATT_Name_one_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        } else if (playerNum == 1) {
            ATT_Name_two_team.clear();
            ATT_Team_two.clear();
            for (int i = 0; i < Attackers.size(); i++) {
                if (Attackers.get(i).getTeam() == id) {
                    ATT_Team_two.add(Attackers.get(i));
                    ATT_Name_two_team.add(Attackers.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ATT_Name_two_team);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            S.setAdapter(adapter);
        } else if (playerNum == 2) {
            ATT_Name_three_team.clear();
            ATT_Team_three.clear();
            for (int i = 0; i < Attackers.size(); i++) {
                if (Attackers.get(i).getTeam() == id) {
                    ATT_Team_three.add(Attackers.get(i));
                    ATT_Name_three_team.add(Attackers.get(i).getName());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ATT_Name_three_team);
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

    private void OpenOutput(){
        MT.addAll(GP.getPlayers());
        Intent i = new Intent(this , Output.class);
        i.putExtra("ManagerTeam", MT);
        i.putExtra("GP", GP);
        startActivity(i);
    }
}