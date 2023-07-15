package com.thegiantgames;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

    TextView tv_vsComputer , tv_multiplayer  , dialog_btn_play;
    EditText cross_name , zero_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("names" , MODE_PRIVATE);



        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float screenHeight = displayMetrics.heightPixels;
        float screenWidth = displayMetrics.widthPixels;

        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_player_name);
        dialog.getWindow().setLayout((int) screenWidth, (int) (screenHeight*0.5f));
        cross_name = dialog.findViewById(R.id.dialog_edittext_cross);
        zero_name = dialog.findViewById(R.id.dialog_edittext_zero);
        dialog_btn_play = dialog.findViewById(R.id.dialog_btn_play);

        tv_multiplayer = findViewById(R.id.tv_multiplayer);



        tv_multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                dialog_btn_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((cross_name.getText().toString().equals("")) || zero_name.getText().toString().equals("")){
                            Toast.makeText(MainActivity.this, "Enter The Names", Toast.LENGTH_SHORT).show();
                        }else {

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("cross_name" , cross_name.getText().toString());
                            editor.putString("zero_name" , zero_name.getText().toString());
                            editor.apply();

                            Intent intent  =new Intent(MainActivity.this , GamePlay.class);
                            intent.putExtra("cross_name" , cross_name.getText().toString());
                            intent.putExtra("zero_name" , zero_name.getText().toString());
                            startActivity(intent);
                        }
                    }
                });

            }
        });





    }
}