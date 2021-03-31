package com.example.fyp.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fyp.R;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    // Static variables
    private static String TAG = "GalleryFragment";

    // Primitive and string variables
//    private int GK_one_id = 0;
//    private int Spinner_id;
//
    private GalleryViewModel galleryViewModel;
//    private ArrayList<String> Teams = new ArrayList<String>();
//
//    // Android Components
//    private Spinner GoalKeeper_Name_one, GoalKeeper_Name_two, Goalkeeper_Team_one, Goalkeeper_Team_two;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });



//        Goalkeeper_Team_one = (Spinner) root.findViewById(R.id.goalkeeper_one_team);
//        GoalKeeper_Name_one = (Spinner) root.findViewById(R.id.goalkeeper_one);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.teams, android.R.layout.simple_spinner_item);

//        Goalkeeper_Team_one.setAdapter(adapter);
//
//        Goalkeeper_Team_one.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                GK_one_id = parent.getSelectedItemPosition();
//                String id2 = Integer.toString(GK_one_id);
//                Log.d(TAG, "position = " + id2);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.d(TAG, "position = not selected");
//            }
//        });


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Teams);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return root;
    }


//    private void showData(DataSnapshot dataSnapshot) {
//        int Team = GK_one_id + 1;
//
//
//        int count = 1;
////        for(DataSnapshot ds : dataSnapshot.getChildren()){
////             NGP = new GP();
////            String Address = ds.child("Address").getValue(String.class);
////            String Name  = ds.child("Name").getValue(String.class);
////            Object Num = ds.child("Phone").getValue();
////            String Email = ds.child("Email").getValue(String.class);
////            Object Age = ds.child("Age").getValue();
////            Object GPID = ds.child("GP_id").getValue();
////
////
////            NGP.setName(Name);
////            NGP.setAddress(Address);
////            NGP.setNum(Num.toString());
////            NGP.setEmail(Email);
////            NGP.setAge(Integer.parseInt(Age.toString()));
////            NGP.setGP_id(GPID.toString());
////            AR.add(NGP);
////
////
////        }
////        for(int i = 0; i < AR.size(); i++){
////            SAR.add(AR.get(i).getName());
////        }
////        textView.setText(SAR.get(0));
//
////
////
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
////                android.R.layout.simple_spinner_item, SAR);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        S.setAdapter(adapter);
//
//    }
}