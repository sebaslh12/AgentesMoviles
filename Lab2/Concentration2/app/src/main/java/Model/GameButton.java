package Model;

import android.graphics.Color;

/**
 * Created by Sebas on 5/03/2016.
 */
public class GameButton {

    private int color;

    private int code;

    public GameButton(int color, int code) {
        this.color = color;
        this.code = code;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
