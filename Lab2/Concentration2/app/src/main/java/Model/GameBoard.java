package Model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebas on 5/03/2016.
 * Clase que contiene la lógica del juego
 * Jugadores, Botones.
 */
public class GameBoard {
    private ArrayList<Player> players;
    private Map<Integer,GameButton> buttons;
    private boolean isFinished;
    //Looking for couple
    private boolean couple;
    int leftCouples;
    private List<Integer> colors = Arrays.asList(Color.parseColor("#0000FF"),Color.parseColor("#FF0000"),Color.parseColor("#A52A2A"),Color.parseColor("#FFD700"),
            Color.parseColor("#006400"),Color.parseColor("#800080"),Color.parseColor("#FFFF00"),Color.parseColor("#7CFC00"));

    public GameBoard(int size) {
        this.buttons = new HashMap<Integer, GameButton>();
        this.players = new ArrayList<Player>();
        this.couple = false;
        this.isFinished = false;
        this.leftCouples = size/2;
        for(int i = 0; i<size;i++){
            GameButton b = new GameButton(colors.get((Integer) i/2),i);
            this.buttons.put(i,b);
        }
    }
    public boolean finished(){
        if(this.leftCouples > 0) {
            return false;
        }else return true;
    }

    public void updatePlayer(int pos, Player p){
        this.players.set(pos,p);
    }

    public void flippedButton(){
        this.couple = true;
    }

    public void addPlayer(Player p){
        this.players.add(p);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Map<Integer, GameButton> getButtons() {
        return buttons;
    }

    public void setButtons(Map<Integer, GameButton> buttons) {
        this.buttons = buttons;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isCouple() {
        return couple;
    }

    public void setCouple(boolean couple) {
        this.couple = couple;
    }

    public int getLeftCouples() {
        return leftCouples;
    }

    public void setLeftCouples(int leftCouples) {
        this.leftCouples = leftCouples;
    }
}

