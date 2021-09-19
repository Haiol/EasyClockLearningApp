package com.lovehp30.easyclocklearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class LearningClockActivity extends AppCompatActivity {
    Button preBtn,nxtBtn,userOneAnswer,userTwoAnswer;
    ClockView clockBoard;
    int map[][],pivot=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learing_clock);

        clockBoard = findViewById(R.id.clockBoard);
        preBtn = findViewById(R.id.preBtn);
        nxtBtn = findViewById(R.id.nxtBtn);
        userOneAnswer = findViewById(R.id.answer1);
        userTwoAnswer = findViewById(R.id.answer2);

//        clockBoard.invalidate();
        map = new int[10][3];

        createMap();
        setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);


        userOneAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map[pivot][2]==0)
                    Log.e("Data","00000000000");
            }
        });
        userTwoAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map[pivot][2]==1)
                    Log.e("Data","11111111111");
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pivot>0)pivot--;
                setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);
            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pivot<10)pivot++;
                setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);
            }
        });


    }
    public void setClockBoard(int h,int y,int turn){
        clockBoard.updateHand(h,y);
        if(turn == 0){
            userOneAnswer.setText(h+"."+y);
            userTwoAnswer.setText(getMinToHour(y)+"."+getHourToMin(h,y));
        }else{
            userTwoAnswer.setText(h+"."+y);
            userOneAnswer.setText(getMinToHour(y)+"."+getHourToMin(h,y));
        }

        clockBoard.invalidate();
    }
    public void createMap(){
        Random rand = new Random();
        //create map
        for(int i=0;i<10;i++){
            map[i][0] = rand.nextInt(11)+1;
            map[i][1] = rand.nextInt(59)+1;
            map[i][2] = rand.nextInt(2);
        }
    }
    public int getHourToMin(int hour,int min){
        return hour*5+(int)(min*0.09);
    }
    public int getMinToHour(int min){
        return min/5;
    }
}