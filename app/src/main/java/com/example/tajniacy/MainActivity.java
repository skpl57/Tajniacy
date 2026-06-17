package com.example.tajniacy;

import static android.widget.LinearLayout.HORIZONTAL;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
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
        LinearLayout main = findViewById(R.id.main);

        for(int i = 0; i < 20; i++){
            Log.d("i", i + "");
            if(i % 4 == 0){
                row = new LinearLayout(this);
                row.setOrientation(HORIZONTAL);
                Log.d("modulo", i + "");
                main.addView(row);
            }
            button = new Button(this);
            button.setText("miał");
            row.addView(button);


        }






    }
}