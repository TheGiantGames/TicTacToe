package com.thegiantgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class GamePlay extends AppCompatActivity {


    ImageView grid1 ,grid2 ,grid3,grid4, grid5,grid6 ,grid7 ,grid8 , grid9;
    int chance  , counter;
    boolean check, gameOver = true;
    String gt1 ,gt2,gt3,gt4,gt5,gt6,gt7,gt8,gt9 , cross_name , zero_name , name1 , name2;
    TextView tv_cross_name, tv_zero_name , dialog_tv_winner , dialog_btn_reset , dialog_btn_home , tv_turn;
    Dialog dialog;

    MediaPlayer player_win , player_draw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Random random =new Random();
        chance = random.nextInt(2);

        tv_turn = findViewById(R.id.tv_turn);
        player_win = MediaPlayer.create(GamePlay.this ,R.raw.win );
        player_draw = MediaPlayer.create(GamePlay.this , R.raw.lost);

        tv_cross_name = findViewById(R.id.cross_name);
        tv_zero_name = findViewById(R.id.zero_name);
        cross_name = getIntent().getStringExtra("cross_name");
       zero_name = getIntent().getStringExtra("zero_name");

        SharedPreferences preferences = getSharedPreferences("names" , MODE_PRIVATE);


        name1 = preferences.getString("cross_name" , "asdf");
        name2 = preferences.getString("zero_name" , "pqrs");


        Log.v("name1" , name1);
        Log.v("name2" , name2);


        tv_cross_name.setText(name1);
       tv_zero_name.setText(name2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float screenHeight = displayMetrics.heightPixels;
        float screenWidth = displayMetrics.widthPixels;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (screenWidth/3.3), (int) (screenWidth/3));




        grid1 = findViewById(R.id.grid1);
        grid1.setLayoutParams(layoutParams);
        grid2 = findViewById(R.id.grid2);
        grid2.setLayoutParams(layoutParams);
        grid3 = findViewById(R.id.grid3);
        grid3.setLayoutParams(layoutParams);
        grid4 = findViewById(R.id.grid4);
        grid4.setLayoutParams(layoutParams);
        grid5 = findViewById(R.id.grid5);
        grid5.setLayoutParams(layoutParams);
        grid6 = findViewById(R.id.grid6);
        grid6.setLayoutParams(layoutParams);
        grid7 = findViewById(R.id.grid7);
        grid7.setLayoutParams(layoutParams);
        grid8 = findViewById(R.id.grid8);
        grid8.setLayoutParams(layoutParams);
        grid9 = findViewById(R.id.grid9);
        grid9.setLayoutParams(layoutParams);


        dialog = new Dialog(GamePlay.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.gameover_dialog);
        dialog.getWindow().setLayout((int) (screenWidth), WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_tv_winner = dialog.findViewById(R.id.dialog_tv_player_won);
        dialog_btn_reset = dialog.findViewById(R.id.dialog_btn_reset);
        dialog_btn_home = dialog.findViewById(R.id.dialog_btn_home);
        dialog_btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               grid1.setImageDrawable(null);
               grid2.setImageDrawable(null);
               grid3.setImageDrawable(null);
               grid4.setImageDrawable(null);
               grid5.setImageDrawable(null);
               grid6.setImageDrawable(null);
               grid7.setImageDrawable(null);
               grid8.setImageDrawable(null);
               grid9.setImageDrawable(null);
               dialog.dismiss();
               counter = 0;
               gameOver = true;
            }
        });

        dialog_btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GamePlay.this , MainActivity.class));
                gameOver = true;
            }
        });


        if (chance == 0){
            tv_turn.setText(name2.toUpperCase() + " TURN");
        } else if (chance == 1) {
            tv_turn.setText(name1.toUpperCase() + " TURN");

        }


    }




    public void play(View view) {

        ImageView currentView = (ImageView) view;
        if (gameOver) {
            check = ((ImageView) view).getDrawable() == null;


            if (check) {
                Log.v("random", "" + chance);
                if (chance == 0) {
                    currentView.setImageDrawable(getDrawable(R.drawable.o));
                    tv_turn.setText(name1.toUpperCase() + " TURN");
                    currentView.setTag(R.drawable.o);
                    chance = 1;
                } else if (chance == 1) {
                    currentView.setImageDrawable(getDrawable(R.drawable.x));
                    currentView.setTag(R.drawable.x);
                    tv_turn.setText(name2.toUpperCase() + " TURN");
                    chance = 0;
                }

                counter++;
                gt1 = "" + grid1.getTag();
                gt2 = "" + grid2.getTag();
                gt3 = "" + grid3.getTag();
                gt4 = "" + grid4.getTag();
                gt5 = "" + grid5.getTag();
                gt6 = "" + grid6.getTag();
                gt7 = "" + grid7.getTag();
                gt8 = "" + grid8.getTag();
                gt9 = "" + grid9.getTag();

                Log.v("x tag", gt1);


                if (gt1.equals(gt2) && gt2.equals(gt3) && !gt1.equals("null") && grid1.getDrawable() != null && grid2.getDrawable() != null && grid3.getDrawable() != null) {
                    winner();
                } else if (gt4.equals(gt5) && gt5.equals(gt6) && !gt4.equals("null") && grid4.getDrawable() != null && grid5.getDrawable() != null && grid6.getDrawable() != null) {
                    winner2();
                } else if (gt7.equals(gt8) && gt8.equals(gt9) && !gt7.equals("null") && grid7.getDrawable() != null && grid8.getDrawable() != null && grid9.getDrawable() != null) {
                    winner1();
                } else if (gt1.equals(gt4) && gt4.equals(gt7) && !gt1.equals("null") && grid1.getDrawable() != null && grid4.getDrawable() != null && grid7.getDrawable() != null) {
                    winner();
                } else if (gt2.equals(gt5) && gt5.equals(gt8) && !gt2.equals("null") && grid2.getDrawable() != null && grid5.getDrawable() != null && grid8.getDrawable() != null) {
                    winner2();
                } else if (gt3.equals(gt6) && gt6.equals(gt9) && !gt3.equals("null") && grid3.getDrawable() != null && grid6.getDrawable() != null && grid9.getDrawable() != null) {
                    winner1();
                } else if (gt1.equals(gt5) && gt5.equals(gt9) && !gt1.equals("null") && grid1.getDrawable() != null && grid5.getDrawable() != null && grid9.getDrawable() != null) {
                    winner();
                } else if (gt3.equals(gt5) && gt5.equals(gt7) && !gt3.equals("null") && grid3.getDrawable() != null && grid5.getDrawable() != null && grid7.getDrawable() != null) {
                    winner2();
                } else if (counter == 9) {
                    player_draw.start();
                    dialog.show();
                    dialog_tv_winner.setText("MATCH DRAW!");
                }
            }
        }

        }

    public void winner(){
        player_win.start();
        gameOver = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.show();
                if (gt9.equals("2131165340")){
                    dialog_tv_winner.setText(zero_name.toUpperCase()+ " Won The Game");
                }else {
                    dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
                }            }
        }, 1000);

    }
    public void winner1(){
        player_win.start();
        gameOver = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.show();
                if (gt9.equals("2131165340")){
                    dialog_tv_winner.setText(zero_name.toUpperCase()+ " Won The Game");
                }else {
                    dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
                }            }
        }, 1000);

    }
    public void winner2(){
        player_win.start();
        gameOver = false;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.show();
                if (gt9.equals("2131165340")){
                    dialog_tv_winner.setText(zero_name.toUpperCase()+ " Won The Game");
                }else {
                    dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
                }            }
        }, 1000);
    }



    }
