package com.lovehp30.easyclocklearningapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class LearningClockActivity extends AppCompatActivity {
    public int GameType=0;
    private  int LifePoint = 0;
    Button preBtn,nxtBtn,userOneAnswer,userTwoAnswer;
    ImageView cat1,cat2;
    ClockView clockBoard;
    ImageView life[];
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
        cat1 = findViewById(R.id.cat1);
        cat2 = findViewById(R.id.cat2);
        life = new ImageView[]{findViewById(R.id.heart1),
                findViewById(R.id.heart2),findViewById(R.id.heart3)};

        Intent intent =getIntent();
        GameType=intent.getExtras().getInt("GAME");


//        clockBoard.invalidate();
        map = new int[10][3];

        createMap();
        setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);


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
                if(pivot<9)pivot++;
                setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);
            }
        });


    }
    public void clickBtnEvent(View v){
        boolean correct;
        if(map[pivot][2]==1){
            if(v.getId() == R.id.answer2)correct = true;
            else correct = false;
        }else{
            if(v.getId() == R.id.answer1)correct = true;
            else correct = false;
        }
        if(correct){
            Toast.makeText(getApplicationContext(),"정답",Toast.LENGTH_LONG).show();
            pivot++;
            setClockBoard(map[pivot][0],map[pivot][1],map[pivot][2]);
            cat1.setImageDrawable(getResources().getDrawable(R.drawable.yes));
            cat2.setImageDrawable(getResources().getDrawable(R.drawable.yes));
        }
        else {
            Toast.makeText(getApplicationContext(), "오답", Toast.LENGTH_LONG).show();
            life[--LifePoint].setVisibility(View.INVISIBLE);
            cat1.setImageDrawable(getResources().getDrawable(R.drawable.no));
            cat2.setImageDrawable(getResources().getDrawable(R.drawable.no));

        }


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        cat1.setImageDrawable(getResources().getDrawable(R.drawable.cat_1));
//                        cat2.setImageDrawable(getResources().getDrawable(R.drawable.cat_1));
//                    }
//                });
//            }
//        }).start();

    }
    public void setClockBoard(int h,int y,int turn){
        clockBoard.updateHand(h,y);
        if(turn == 0){
            userOneAnswer.setText(h+":"+y);
            userTwoAnswer.setText(getMinToHour(y)+":"+getHourToMin(h,y));
        }else{
            userTwoAnswer.setText(h+":"+y);
            userOneAnswer.setText(getMinToHour(y)+":"+getHourToMin(h,y));
        }

        clockBoard.invalidate();
    }
    public void createMap(){
        LifePoint = 3;
        for(ImageView view:life)
            view.setVisibility(View.VISIBLE);
        Random rand = new Random();
        //create map

        switch (GameType){
            case MainActivity.EASY:
                for(int i=0;i<10;i++){
                    map[i][0] = rand.nextInt(11)+1;
                    map[i][1] = 0;
                    map[i][2] = rand.nextInt(2);
                }
                break;
            case MainActivity.NORMAL:
                for(int i=0;i<10;i++){
                    map[i][0] = rand.nextInt(11)+1;
                    map[i][1] = (rand.nextInt(11)+1)*5;
                    map[i][2] = rand.nextInt(2);
                }
                break;
            case MainActivity.HARD:
                for(int i=0;i<10;i++){
                    map[i][0] = rand.nextInt(11)+1;
                    map[i][1] = rand.nextInt(59)+1;
                    map[i][2] = rand.nextInt(2);
                }
                break;
        }


    }
    public int getHourToMin(int hour,int min){
        return hour*5+(int)(min*0.09);
    }
    public int getMinToHour(int min){
        return min/5==0?12:min/5;
    }
}