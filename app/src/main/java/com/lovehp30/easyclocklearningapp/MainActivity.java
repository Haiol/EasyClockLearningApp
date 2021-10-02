package com.lovehp30.easyclocklearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    public static final int EASY = 1;
    public static final int NORMAL = 2;
    public static final int HARD = 3;

    public int GameRules = 0;

    CheckBox checkES, checkNM, checkHD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkES = findViewById(R.id.checkEasy);
        checkNM = findViewById(R.id.checkNormal);
        checkHD = findViewById(R.id.checkHard);
        findViewById(R.id.startBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LearningClockActivity.class);
                intent.putExtra("GAME",GameRules);
                startActivity(intent);
            }
        });

    }

    public void onCheckBoxClicked(View v) {
        switch (v.getId()) {
            case R.id.checkEasy:
                GameRules = EASY;
                checkES.setChecked(true);
                checkNM.setChecked(false);
                checkHD.setChecked(false);
                break;
            case R.id.checkNormal:
                GameRules = NORMAL;
                checkES.setChecked(false);
                checkNM.setChecked(true);
                checkHD.setChecked(false);
                break;
            case R.id.checkHard:
                GameRules = HARD;
                checkES.setChecked(false);
                checkNM.setChecked(false);
                checkHD.setChecked(true);
                break;
        }

    }
}