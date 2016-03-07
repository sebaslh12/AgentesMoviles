package com.tarea1.moviles.concentration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Model.GameBoard;
import Model.GeneralMenu;
import Model.Player;

public class getPlayersActivity extends GeneralMenu {
    //GameLogic static so the next activity will extend this an get all this attributes

    EditText playerName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Size of the board (Number of cards)
        this.game = new GameBoard(16);
    }

    public void addPlayer(View view) {
        playerName = (EditText) findViewById(R.id.playerName);
        if(!playerName.getText().toString().isEmpty()) {
            game.addPlayer(new Player(playerName.getText().toString()));
            Toast.makeText(getPlayersActivity.this, playerName.getText().toString()+ " ready!", Toast.LENGTH_LONG).show();
            playerName.setText("");

        }else Toast.makeText(getPlayersActivity.this, "The name is empty!", Toast.LENGTH_LONG).show();
    }

    public void goGame(View view) {
        if(!game.getPlayers().isEmpty()) {
            Intent intent = new Intent(this, gameBoardActivity.class);
            startActivity(intent);
        }else  Toast.makeText(getPlayersActivity.this, "At least 1 player must be added", Toast.LENGTH_LONG).show();
    }
}
