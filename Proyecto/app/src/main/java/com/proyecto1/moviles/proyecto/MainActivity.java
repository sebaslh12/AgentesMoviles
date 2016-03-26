package com.proyecto1.moviles.proyecto;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Model.Services.GetContactsService;
import Model.listCustom;

public class MainActivity extends Activity {

    ListView listaContactos;
    Button msg;
    Button files;
    Integer[] imagenes = {R.drawable.user};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        try {
            final ArrayList<String> consulta = new GetContactsService().execute("3").get();
            //ArrayAdapter<String> dataArray= new ArrayAdapter<String>(this,R.layout.contact_view, consulta);
            //this.setListAdapter(dataArray);

            listCustom adapter = new listCustom(MainActivity.this, consulta, imagenes);
            listaContactos=(ListView)findViewById(R.id.list);
            listaContactos.setAdapter(adapter);


            listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "Escogio " + position, Toast.LENGTH_SHORT).show();
                }

            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** llamado cuando el usuario selecciona el boton de mensaje */
    public void viewMessage(View view) {
        View parentRow = (View) view.getParent();
        final int position = listaContactos.getPositionForView(parentRow);
        Toast.makeText(MainActivity.this, "Escogio "+position , Toast.LENGTH_SHORT).show();

    }


}
