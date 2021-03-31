package com.example.fyp.Team_input.Opening;

import android.content.Intent;
import android.os.Bundle;

import com.example.fyp.Entity.GatherPlayers;
import com.example.fyp.Entity.GlobalVariable;
import com.example.fyp.Team_input.Goalkeepers.Goalkeeper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fyp.R;

import java.util.ArrayList;

public class Opening_input extends AppCompatActivity {
    private static final String TAG = "OPENING INPUT";

    private ArrayList<Float> AR = new ArrayList<Float>();
    private Spinner itb;
    private Button Next;

    private GatherPlayers GP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_input);
        populateArrayList();
        GP = new GatherPlayers();
        Bundle extras = getIntent().getExtras();
        itb = findViewById(R.id.spinner2);
        ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, AR);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itb.setAdapter(adapter);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Next = findViewById(R.id.Change_To_GoalKeepers);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Change activity clicked");

                OpenDefendersActivity();
            }
        });
        setSupportActionBar(toolbar);
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

    private void populateArrayList() {
        float f = 10.0f;
        for(int i = 0; i<151; i++){
            float num = i/f;
            AR.add(num);
        }
    }

    private void OpenDefendersActivity(){

        if(itb.getSelectedItem()== null)
        {
            Toast.makeText(this,"Please input your in the bank value",Toast.LENGTH_LONG).show();
        }
        else{
            Intent i = new Intent(this , Goalkeeper.class);
            Float f = (Float) itb.getSelectedItem();
            i.putExtra("IN THE BANK", f);
            i.putExtra("GP", GP);
            startActivity(i);
        }
    }
}