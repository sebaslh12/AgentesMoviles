package com.proyecto1.moviles.proyecto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import Model.Classes.MessageDB;
import Model.Classes.ResultMessages;
import Model.Services.GetMessagesService;

public class Messages_View extends ListActivity {

    private Integer idUser;
    private ArrayList<String> msgsEnviados;
    private ArrayList<String> msgsRecibidos;
    private ArrayList<String> conversacion;
    //Usuario de la sesión
    private final String User = "1";
    private MessageDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB = new MessageDB(this,null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages__view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent myIntent = getIntent(); // Gets the previously created intent
        idUser = myIntent.getIntExtra("idUser", 0);

        try {
            //Cambiar el "3" cuando se cambia de usuario que envia los mensajes
            msgsEnviados = new GetMessagesService().execute(User, idUser.toString()).get();
            msgsRecibidos = new GetMessagesService().execute(idUser.toString(), User).get();
            setAdapterService();
            if (conversacion.size() > 0) {
                ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.contact_view, conversacion);
                this.setListAdapter(dataArray);
            } else {
                DBQuery();
                if (conversacion.size() > 0) {
                    ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.contact_view, conversacion);
                    this.setListAdapter(dataArray);
                }else {
                    Toast.makeText(Messages_View.this, "No hay mensajes", Toast.LENGTH_LONG).show();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void DBQuery(){
        msgsRecibidos = DB.findMessages(idUser.toString(), User);
        msgsEnviados = DB.findMessages(User,idUser.toString());
        Iterator<String> env = msgsEnviados.iterator();
        Iterator<String> rec = msgsRecibidos.iterator();
        while ((env.hasNext()) || (rec.hasNext()) ) {
            if(env.hasNext()){
                String tmpsend = env.next();
                conversacion.add(tmpsend);
            }
            if(rec.hasNext()){
                String tmprec = rec.next();
                conversacion.add(tmprec);
            }
        }
    }
    //Setting the adapter after calling the services
    public void setAdapterService(){
        Iterator<String> env = msgsEnviados.iterator();
        Iterator<String> rec = msgsRecibidos.iterator();
        ResultMessages itemDBReceive = new ResultMessages();
        ResultMessages itemDBSent = new ResultMessages();
        //Items Receive to DB
        itemDBReceive.setTo(Integer.parseInt(User));
        itemDBReceive.setFrom(idUser);
        //Items sent to DB
        itemDBSent.setFrom(Integer.parseInt(User));
        itemDBSent.setTo(idUser);
        conversacion = new ArrayList<String>();
        while ((env.hasNext()) || (rec.hasNext()) ) {
            if(env.hasNext()){
                String tmpsend = env.next();
                conversacion.add(tmpsend);
                itemDBReceive.setText(tmpsend);
                DB.addMessage(itemDBReceive);
            }
            if(rec.hasNext()){
                String tmprec = rec.next();
                conversacion.add(tmprec);
                itemDBSent.setText(tmprec);
                DB.addMessage(itemDBSent);
            }
        }
    }

}
