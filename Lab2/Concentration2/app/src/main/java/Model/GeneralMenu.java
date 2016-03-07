package Model;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tarea1.moviles.concentration.R;
import com.tarea1.moviles.concentration.getPlayersActivity;

/**
 * Created by Sebas on 5/03/2016.
 */
public class GeneralMenu extends AppCompatActivity{
    protected static GameBoard game;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.about:
                aboutMenu();
                break;
            case R.id.reset:
                this.game = new GameBoard(16);
                Intent intent = new Intent(this,getPlayersActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    private void aboutMenu() {
        new AlertDialog.Builder(this)
        .setTitle("About")
        .setMessage("This App was made by Sebastian Lozano (sebaslh12@gmail.com) using Android Studio.")
        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();

    }


}
