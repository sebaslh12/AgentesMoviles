package com.proyecto1.moviles.proyecto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import Model.ResultFile;
import Model.Services.GetMessagesService;
import Model.Services.GetSharedFilesService;

public class Messages_View extends ListActivity {

    private Integer idUser;
    private ArrayList<String> msgsEnviados;
    private ArrayList<String> msgsRecibidos;
    private ArrayList<String> conversacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages__view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent myIntent = getIntent(); // Gets the previously created intent
        idUser = myIntent.getIntExtra("idUser", 0);

        try {
            //Cambiar el "3" cuando se cambia de usuario que envia los mensajes
            msgsEnviados = new GetMessagesService().execute("3", idUser.toString()).get();
            msgsRecibidos = new GetMessagesService().execute(idUser.toString(), "3").get();
            Iterator<String> env = msgsEnviados.iterator();
            Iterator<String> rec = msgsRecibidos.iterator();
            conversacion = new ArrayList<String>();
            while ((env.hasNext()) || (rec.hasNext()) ) {
                if(env.hasNext()){
                    conversacion.add(env.next());
                }
                if(rec.hasNext()){
                    conversacion.add(rec.next());
                }
            }
            /*if (msgsEnviados.size() > 0) {
                ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.contact_view, msgs);
                this.setListAdapter(dataArray);
            } else {
                Toast.makeText(Messages_View.this, "No hay mensajes", Toast.LENGTH_LONG).show();
            }*/

            if (conversacion.size() > 0) {
                ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.contact_view, conversacion);
                this.setListAdapter(dataArray);
            } else {
                Toast.makeText(Messages_View.this, "No hay mensajes", Toast.LENGTH_LONG).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
