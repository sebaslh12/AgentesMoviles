package com.tarea1.moviles.concentration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Model.GameBoard;
import Model.GeneralMenu;
import Model.Player;
import Model.PlayerSort;

public class winnerActivity extends GeneralMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showWinners();
    }

    public void showWinners(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.winners);
        new PlayerSort().sort(this.game.getPlayers());
        ArrayList<Player> players = this.game.getPlayers();
        for (int i = 0; i<players.size();i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            Player p = players.get(i);
            TextView name = new TextView(this);
            TextView score = new TextView(this);
            name.setText(p.getName());
            score.setText(Integer.toString(p.getScore()));
            //Setting the names
            name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            name.setGravity(Gravity.CENTER);
            //Setting the scores
            score.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            score.setGravity(Gravity.CENTER);
            row.addView(name);
            row.addView(score);
            layout.addView(row);
        }
    }

    public void PlayAgain(View view) {
        this.game = new GameBoard(16);
        Intent intent = new Intent(this,getPlayersActivity.class);
        startActivity(intent);
    }

    public void Close(View view) {
        //Finish all activities
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
