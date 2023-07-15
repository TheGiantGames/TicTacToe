package com.thegiantgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BotPlay extends AppCompatActivity {

    ImageView grid1 ,grid2 ,grid3,grid4, grid5,grid6 ,grid7 ,grid8 , grid9;
    String gt1 ,gt2,gt3,gt4,gt5,gt6,gt7,gt8,gt9 ,zero_name ,cross_name  ;

    boolean yourTurn = true;
    TextView  dialog_tv_winner , dialog_btn_reset , dialog_btn_home;

    Dialog dialog;

    ArrayList<String> list;

    int chance  , counter , turn , position, used_position ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_play);

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


        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");


        Random random = new Random();
        turn = random.nextInt(2);
        Timer timer = new Timer();
        if (counter != 9){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {



                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (counter != 9) {
                            used_position = random.nextInt(list.size());
                            position = Integer.parseInt(list.get(used_position));
                            if (!yourTurn) {
                                botPlay();
                                list.remove(used_position);
                                counter++;
                            }

                            yourTurn = true;
                        }
                    }
                });

            }
        } , 0 , 1000);}


        dialog = new Dialog(BotPlay.this);
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
            }
        });

        dialog_btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BotPlay.this , MainActivity.class));
            }
        });


    }

    public void play(View view) {
        if (yourTurn) {
            ImageView currentView = (ImageView) view;
            boolean check = ((ImageView) view).getDrawable() == null;
            if (check) {
                if (chance == 0) {
                    currentView.setImageDrawable(getDrawable(R.drawable.o));
                    currentView.setTag(R.drawable.o);
                } else if (chance == 1) {
                    currentView.setImageDrawable(getDrawable(R.drawable.x));
                    currentView.setTag(R.drawable.x);
                }

                if (view == grid1){
                   list.remove("1");
                }else if (view == grid2){
                    list.remove("2");
                }else if (view == grid3){
                    list.remove("3");
                }else if (view == grid4){
                    list.remove("4");
                }else if (view == grid5){
                    list.remove("5");
                }else if (view == grid6){
                    list.remove("6");
                }else if (view == grid7){
                    list.remove("7");
                }else if (view == grid8){
                    list.remove("8");
                }else if (view == grid9){
                    list.remove("9");
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
                     dialog.show();
                      dialog_tv_winner.setText("MATCH DRAW!");
                }
                yourTurn = false;
            }



        }

       // if (!yourTurn){
         //   botPlay();
        //}


    }

    public void botPlay(){
        if (position == 1){
            grid1.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 2) {
            grid2.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 3) {
            grid3.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 4) {
            grid4.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 5) {
            grid5.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 6) {
            grid6.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 7) {
            grid7.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 8) {
            grid8.setImageDrawable(getDrawable(R.drawable.x));
        } else if (position == 9) {
            grid9.setImageDrawable(getDrawable(R.drawable.x));
        }


    }

    public void winner(){
        dialog.show();
        if (gt1.equals("2131165340")){
            dialog_tv_winner.setText(zero_name.toUpperCase()+ " Won The Game");
        }else {
            dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
        }
    }
    public void winner1(){
        dialog.show();
        if (gt9.equals("2131165340")){
            dialog_tv_winner.setText(zero_name.toUpperCase()+ " Won The Game");
        }else {
            dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
        }
    }
    public void winner2() {
        dialog.show();
        if (gt5.equals("2131165340")) {
            dialog_tv_winner.setText(zero_name.toUpperCase() + " Won The Game");
        } else {
            dialog_tv_winner.setText(cross_name.toUpperCase() + " Won the Game");
        }
    }

    public void win(){

    }
}