package com.proyecto1.moviles.proyecto;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import Model.GetFiles;
import Model.ResultFile;
import Model.Services.GetContactsService;
import Model.Services.GetSharedFilesService;
import Model.listCustom;

public class Files_View extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files__view);
        GetFiles files = null;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent myIntent = getIntent(); // gets the previously created intent
        Integer firstKeyName = myIntent.getIntExtra("idUser", 0); // will return "FirstKeyValue"
        try {
            files = new GetSharedFilesService().execute(firstKeyName.toString(),"2").get();
            ArrayList<String> consulta = new ArrayList<String>();
            if(!files.getData().isEmpty()){
                for(ResultFile f : files.getData()){
                    consulta.add("Archivo: "+ f.getName()+" "+f.getDate());
                }
                ArrayAdapter<String> dataArray= new ArrayAdapter<String>(this,R.layout.contact_view, consulta);
                this.setListAdapter(dataArray);

            }else {
                Toast.makeText(Files_View.this, "Esta persona ha compartido archivos con usted." , Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



    }

}
