package com.proyecto1.moviles.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Model.Classes.ResultUser;
import Model.Services.GetContactsService;
import Model.Classes.listCustom;

public class MainActivity extends Activity {

    ListView listaContactos;
    Button msg;
    Button files;
    Integer[] imagenes = {R.drawable.user};
    ArrayList<ResultUser> consulta;
    private final String User = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        try {
             consulta = new GetContactsService().execute(User).get();
            //ArrayAdapter<String> dataArray= new ArrayAdapter<String>(this,R.layout.msg_view, consulta);
            //this.setListAdapter(dataArray);
            ArrayList<String> result = new ArrayList<String>();
            if(!consulta.isEmpty()){
                for(ResultUser f : consulta){
                    result.add(f.getNombre()+" "+f.getUserName());
                }
                listCustom adapter = new listCustom(MainActivity.this, result, imagenes);
                listaContactos=(ListView)findViewById(R.id.list);
                listaContactos.setAdapter(adapter);
            }else {
                Toast.makeText(MainActivity.this, "Usted no tiene contactos" , Toast.LENGTH_SHORT).show();
            }

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
        Intent msgIntent = new Intent(this,Messages_View.class);
        msgIntent.putExtra("idUser", consulta.get(position).getUserId());
        msgIntent.putExtra("nameUser",consulta.get(position).getUserName());
        startActivity(msgIntent);

    }


    public void viewFiles(View view) {

        View parentRow = (View) view.getParent();
        final int position = listaContactos.getPositionForView(parentRow);
        Intent filesIntent = new Intent(this,Files_View.class);
        filesIntent.putExtra("idUser",consulta.get(position).getUserId());
        startActivity(filesIntent);
    }
}
