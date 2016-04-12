package com.proyecto1.moviles.proyecto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import Model.Classes.MessageDB;
import Model.Classes.ResultMessages;
import Model.Services.GetMessagesService;
import Model.Services.PostMessageService;

public class Messages_View extends ListActivity {

    private Integer idUser;
    //private ArrayList<String> msgsEnviados;
    private ArrayList<String> msgsRecibidos;
    //private ArrayList<String> conversacion;
    //Usuario de la sesión
    private final String User = "1";
    private MessageDB DB;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB = new MessageDB(this,null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages__view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent myIntent = getIntent(); // Gets the previously created intent
        idUser = myIntent.getIntExtra("idUser", 0);
        userName= myIntent.getStringExtra("nameUser");
        //Cambiar el "3" cuando se cambia de usuario que envia los mensajes
        //msgsEnviados = new GetMessagesService().execute(User, idUser.toString()).get();
        refresh();
        //setAdapterService();
    }

    public void DBQuery(){
        msgsRecibidos = DB.findMessages(idUser.toString(), User);
        //msgsEnviados = DB.findMessages(User,idUser.toString());
        //Iterator<String> env = msgsEnviados.iterator();
//        Iterator<String> rec = msgsRecibidos.iterator();
//        while ((env.hasNext()) || (rec.hasNext()) ) {
//            if(env.hasNext()){
//                String tmpsend = env.next();
//                conversacion.add(tmpsend);
//            }
//            if(rec.hasNext()){
//                String tmprec = rec.next();
//                conversacion.add(tmprec);
//            }
//        }
    }
    public void addToDB(){
        ResultMessages itemDBReceive = new ResultMessages();
        itemDBReceive.setTo(Integer.parseInt(User));
        itemDBReceive.setFrom(idUser);
        for(String msg : msgsRecibidos){
            itemDBReceive.setText(userName + ": " + msg);
            DB.addMessage(itemDBReceive);
        }
    }
    //Setting the adapter after calling the services
//    public void setAdapterService(){
//        Iterator<String> env = msgsEnviados.iterator();
//        Iterator<String> rec = msgsRecibidos.iterator();
//        ResultMessages itemDBReceive = new ResultMessages();
//        ResultMessages itemDBSent = new ResultMessages();
//        //Items Receive to DB
//        itemDBReceive.setTo(Integer.parseInt(User));
//        itemDBReceive.setFrom(idUser);
//        //Items sent to DB
//        itemDBSent.setFrom(Integer.parseInt(User));
//        itemDBSent.setTo(idUser);
//        conversacion = new ArrayList<String>();
//        while ((env.hasNext()) || (rec.hasNext()) ) {
//            if(env.hasNext()){
//                String tmpsend = env.next();
//                conversacion.add(tmpsend);
//                itemDBReceive.setText(tmpsend);
//                DB.addMessage(itemDBReceive);
//            }
//            if(rec.hasNext()){
//                String tmprec = userName + ": " + rec.next();
//                conversacion.add(tmprec);
//                itemDBSent.setText(tmprec);
//                DB.addMessage(itemDBSent);
//            }
//        }
//    }

    public void sendMessage(View view){
        EditText messageInput = (EditText) findViewById(R.id.msgbody);
        String messageBody = messageInput.getText().toString();
        messageInput.setText("");
        ResultMessages itemDBSent = new ResultMessages();
        itemDBSent.setTo(Integer.parseInt(User));
        itemDBSent.setFrom(idUser);
        //Update the view adapter
        //(BaseAdapter) mMyListView.getAdapter()).notifyDataSetChanged();
        if(!messageBody.trim().isEmpty()) {
            try {
                new PostMessageService().execute(User, idUser.toString(), messageBody).get();
                itemDBSent.setText("Me: " +messageBody);
                DB.addMessage(itemDBSent);
                refresh();
            } catch (InterruptedException e) {
                Toast.makeText(Messages_View.this, "Mistakes were made", Toast.LENGTH_LONG).show();
            } catch (ExecutionException e) {
                Toast.makeText(Messages_View.this, "Mistakes were made", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(Messages_View.this, "No puedes enviar un mensaje vacio", Toast.LENGTH_LONG).show();
            refresh();
        }
    }

    public void refresh() {
        try {
            msgsRecibidos = new GetMessagesService().execute(idUser.toString(), User).get();
            if (msgsRecibidos.size() > 0) {
                addToDB();
            }else{
                Toast.makeText(Messages_View.this, "No se obtuvieron mensajes nuevos", Toast.LENGTH_LONG).show();
            }
            DBQuery();
            if (msgsRecibidos.size() > 0) {
                //Conversacion if you need both messages
                ArrayAdapter<String> dataArray = new ArrayAdapter<String>(this, R.layout.msg_view, msgsRecibidos);
                this.setListAdapter(dataArray);
            } else {
                Toast.makeText(Messages_View.this, "No hay mensajes guardados", Toast.LENGTH_LONG).show();
            }
        } catch (InterruptedException e) {
            Toast.makeText(Messages_View.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            Toast.makeText(Messages_View.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
        }

    }
}
