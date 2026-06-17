package com.example.tajniacy;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.widget.LinearLayout.HORIZONTAL;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private LinearLayout row;
    private Button button;


    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

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
        sharedPref = getSharedPreferences("AppData", MODE_PRIVATE);
        editor = sharedPref.edit();
        LinearLayout main = findViewById(R.id.main);

        for(int i = 0; i < 20; i++){
            Log.d("i", i + "");
            if(i % 4 == 0){
                row = new LinearLayout(this);
                row.setOrientation(HORIZONTAL);
                row.setGravity(1);
                Log.d("modulo", i + "");
                main.addView(row);
            }
            button = new Button(this);
            button.setText("miał");
            button.setHeight(250);
            button.setWidth(250);
            row.addView(button);


        }






    }
}