package com.tarea1.moviles.concentration;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import Model.GameButton;
import Model.GeneralMenu;
import Model.Player;

public class gameBoardActivity extends GeneralMenu {
    private int turn = 0;
    //First picked card
    private GameButton firstCard;
    //Second card
    private GameButton secondCard;
    //Waiting for the second card
    private boolean secondOne = false;
    //Player's turn
    private Player player = this.game.getPlayers().get(turn);

    int defaultColor = Color.parseColor("#20B2AA");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillBoard();
        Toast.makeText(gameBoardActivity.this, "Is "+ player.getName()+"'s turn", Toast.LENGTH_LONG).show();
    }

    public void fillBoard(){
        /*TableLayout layout = (TableLayout)View.inflate(this,R.layout.activity_game_board,null);
        for (int i = 0; i < this.game.getLeftCouples()*2 ; i++ ){
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < 4 ; j++) {
                Button btn = new Button(this);
                btn.setId(i);
                btn.setBackgroundColor(this.defaultColor);
                btn.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                row.addView(btn);
            }
            layout.addView(row);
        }*/
        //setContentView(layout);

        LinearLayout layout = (LinearLayout) findViewById(R.id.contentGame);
        layout.setOrientation(LinearLayout.VERTICAL);  //Can also be done in xml by android:orientation="vertical"

        for (int i = 0; i < this.game.getButtons().size()/4 ; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < this.game.getButtons().size()/4; j++) {
                Button btnTag = new Button(this);
                btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                //btnTag.setText((j + 1 + (i * 4)));
                btnTag.setBackgroundResource(android.R.drawable.btn_default);
                btnTag.setId(j + (i * 4));
                btnTag.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        flipButton(v);
                        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
                    }
                });
                row.addView(btnTag);
            }
            layout.addView(row);
        }
    }

    public void flipButton(View v){
        //id of the pushed button
        int id = v.getId();
        System.out.println(""+id);
        //If is waiting for a couple
        if(secondOne){
            secondCard = this.game.getButtons().get(id);
            Button buttonFlipped = (Button) findViewById(id);
            buttonFlipped.setBackgroundColor(secondCard.getColor());
            if(firstCard.getColor() == secondCard.getColor() && firstCard.getCode() != secondCard.getCode()){
                buttonFlipped.setClickable(false);
                buttonFlipped = (Button) findViewById(firstCard.getCode());
                buttonFlipped.setClickable(false);
                firstCard = secondCard = null;
                secondOne = false;
                this.game.setLeftCouples(this.game.getLeftCouples() - 1);
                this.player.setScore(this.player.getScore() + 10);
                this.game.updatePlayer(turn, player);
                System.out.println("Points" + player.getScore() +""+ player.getName());
                if(this.game.finished()){
                    Toast.makeText(gameBoardActivity.this, "Game Finished Congratulations", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this,winnerActivity.class);
                    startActivity(intent);
                }
            }else {
                secondOne = false;
                buttonFlipped.setBackgroundResource(android.R.drawable.btn_default);
                buttonFlipped = (Button) findViewById(firstCard.getCode());
                buttonFlipped.setBackgroundResource(android.R.drawable.btn_default);
                firstCard = secondCard = null;
                if (this.game.getPlayers().size()>1) {
                    this.turn = (this.turn+1) % (this.game.getPlayers().size());
                    player = this.game.getPlayers().get(turn);
                    Toast.makeText(gameBoardActivity.this, "You've failed, is " + player.getName() + "'s turn", Toast.LENGTH_SHORT).show();
                }
            }

        }else { //If is the first card
            firstCard = this.game.getButtons().get(id);
            Button buttonFlipped = (Button) findViewById(id);
            buttonFlipped.setBackgroundColor(firstCard.getColor());
            secondOne = true;
        }

    }
}
