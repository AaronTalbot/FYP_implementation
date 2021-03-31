package com.example.fyp.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fyp.Entity.GlobalVariable;
import com.example.fyp.Entity.Player;
import com.example.fyp.R;
import com.example.fyp.Team_input.Opening.Opening_input;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private static String TAG = "Slide Show Fragment";

    private Button Input_team;
    final GlobalVariable Instance = GlobalVariable.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        Input_team = root.findViewById(R.id.Input_Team_slideshow_Button);

        Input_team.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                open_team_input();
            }
        });

        return root;
    }

    private void open_team_input() {
        Intent i = new Intent(getActivity(), Opening_input.class);
        i.putExtra("GloablV", Instance);
        startActivity(i);
    }
}