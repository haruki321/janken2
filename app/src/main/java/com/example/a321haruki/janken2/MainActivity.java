package com.example.a321haruki.janken2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import java.util.Random;

public class MainActivity extends AppCompatActivity{


    private ImageView img1;
    private ImageView img2;
    private int num;
    String numnum="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.text);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);


        final TextView subtext = (TextView)findViewById(R.id.subtext);
        final TextView numtext = (TextView)findViewById(R.id.numtext);

        Button rock = (Button)findViewById(R.id.rock);
        Button scissors = (Button)findViewById(R.id.scissors);
        Button paper = (Button)findViewById(R.id.paper);
        Button next = (Button)findViewById(R.id.next);
        //opponentHand 1  "rock"
        //opponentHand 2  "scissors"
        //opponentHand 3  "paper"

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);
                img1.setImageResource(R.drawable.janken_gu);
                decideGame(1, opponentHand, subtext,numtext);
                if(opponentHand==1){
                    img2.setImageResource(R.drawable.janken_gu);
                }
                if(opponentHand==2){
                    img2.setImageResource(R.drawable.janken_choki);
                }
                if(opponentHand==3){
                    img2.setImageResource(R.drawable.janken_pa);
                }

                text.setText(opponentHandText);
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);

                img1.setImageResource(R.drawable.janken_choki);
                decideGame(2, opponentHand, subtext,numtext);
                if(opponentHand==1){
                    img2.setImageResource(R.drawable.janken_gu);
                }
                if(opponentHand==2){
                    img2.setImageResource(R.drawable.janken_choki);
                }
                if(opponentHand==3){
                    img2.setImageResource(R.drawable.janken_pa);
                }
                text.setText(opponentHandText);

            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opponentHand = decideOpponentHand();
                String opponentHandText = changeTextOpponentHand(opponentHand);
                img1.setImageResource(R.drawable.janken_pa);
                decideGame(3, opponentHand, subtext,numtext);
                if(opponentHand==1){
                    img2.setImageResource(R.drawable.janken_gu);
                }
                if(opponentHand==2){
                    img2.setImageResource(R.drawable.janken_choki);
                }
                if(opponentHand==3){
                    img2.setImageResource(R.drawable.janken_pa);
                }
                text.setText(opponentHandText);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });


    }
    String changeTextOpponentHand(int hand) {
        String handText = "";
        if (hand == 1) handText = "グー";
        else if (hand == 2) handText = "チョキ";
        else if (hand == 3) handText = "パー";
        return handText;
    }
    int decideOpponentHand() {
        Random rnd = new Random();
        int hand = rnd.nextInt(3)+1;
        return hand;
    }
    void decideGame(int playerHand, int opponentHand, TextView decidetext,TextView numtext) {
        String decision;

        if(playerHand == opponentHand) decision = "あいこ";

        else if((playerHand == 3 && opponentHand == 1) || (playerHand+1 == opponentHand)){
            decision = "勝ち" ;
            num++;
        }

        else{
            decision = "負け";
            num--;
        }

        numtext.setText(String.valueOf(num));
        decidetext.setText(decision);

        if(num==3){
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        }

        if(num==-3){
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        }

    }
}
