package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Sebas on 6/03/2016.
 */
public class PlayerSort implements Comparator<Player> {

    public void sort(ArrayList<Player> p){
        Collections.sort(p,new PlayerSort());
    }
    @Override
    public int compare(Player p1, Player p2) {
        return p2.getScore() - p1.getScore();
    }
}
