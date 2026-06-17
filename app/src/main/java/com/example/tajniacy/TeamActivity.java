package com.example.tajniacy;

import static android.widget.LinearLayout.HORIZONTAL;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class TeamActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPref;
    private LinearLayout main;
    private boolean team;
    private final ArrayList<Integer> green = new ArrayList<>();
    private final ArrayList<Integer> black = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        team = getIntent().getBooleanExtra("TEAM", false);
        sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
        editor = sharedPref.edit();
        main = findViewById(R.id.main);

        boolean saved = false;
        if(!team) saved = sharedPref.getBoolean("SAVED_STATE_G1", false);
        else saved = sharedPref.getBoolean("SAVED_STATE_G2", false);

        if(!saved){
            GenerateBoard();
            if(!team) editor.putBoolean("SAVED_STATE_G1", true);
            else editor.putBoolean("SAVED_STATE_G2", true);
            editor.apply();
        }

        GenerateBoard();
        SaveData();
    }

    private void SaveData(){
        String intSequenceGreen = "";
        for(int i : green){
            intSequenceGreen = intSequenceGreen + i;
        }
        String intSequenceBlack = "";
        for(int i : black){
            intSequenceBlack = intSequenceBlack + i;
        }


        if(!team){
            editor.putString("GREEN_SEQUENCE_G1", intSequenceGreen);
            editor.putString("BLACK_SEQUENCE_G1", intSequenceBlack);
        }
        else{
            editor.putString("GREEN_SEQUENCE_G2", intSequenceGreen);
            editor.putString("BLACK_SEQUENCE_G2", intSequenceBlack);
        }
        editor.apply();
    }
    private ArrayList<Integer> DecodeSavedData(String intSequence){
        ArrayList<Integer> intList = new ArrayList<>();

        for(int i = 0; i < intSequence.length(); i++){
            intList.add(Character.getNumericValue(intSequence.charAt(i)));
        }

        return intList;
    }

    private void GenerateBoard(){
        Random random = new Random();

        for (int i = 0; i < 6; i++){
            int index = random.nextInt(20);
            while (green.contains(index)){
                index = random.nextInt(20);
            }
            green.add(index);
        }

        for (int i = 0; i < 3; i++){
            int index = random.nextInt(20);
            while (black.contains(index) || green.contains(index)){
                index = random.nextInt(20);
            }
            black.add(index);
        }
        PaintButtons();
    }

    private void PaintButtons(){
        LinearLayout row = null;
        Button button;
        for(int i = 0; i < 20; i++){
            if(i % 4 == 0){
                row = new LinearLayout(this);
                row.setOrientation(HORIZONTAL);
                row.setGravity(1);
                main.addView(row);
            }

            button = new Button(this);
            button.setText("miał");
            button.setHeight(250);
            button.setWidth(250);

            if(black.contains(i))  button.setBackgroundColor(Color.parseColor("#333333"));
            else if(green.contains(i)) button.setBackgroundColor(Color.parseColor("#07D17F"));
            else button.setBackgroundColor(Color.parseColor("#FFE8FF"));

            row.addView(button);
        }
    }
}
