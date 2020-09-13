package com.example.fishingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gameover extends AppCompatActivity {
     private Button startgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        startgame=(Button)findViewById(R.id.button);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent=new Intent(gameover.this,MainActivity.class);
                startActivity(mainintent);
            }
        });
    }
}
