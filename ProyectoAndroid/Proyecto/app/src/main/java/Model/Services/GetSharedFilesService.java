package Model.Services;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import Model.Classes.GetFiles;

/**
 * Created by Sebas on 25/03/2016.
 */
public class GetSharedFilesService extends AsyncTask<String, Void, GetFiles> {

    public GetFiles doInBackground(String... params) {
            // The connection URL
            GetFiles data = new GetFiles();
            String URL = "http://192.168.0.13:8191/rest/shared_files/{from}/{to}";
            //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(URL, String.class, params[0],params[1]);
            result = "{\"data\":"+ result + "}";
            System.out.println("CONSULTA>>"+result);
            try {
                GetFiles jsonMapp = new ObjectMapper().readValue(result, GetFiles.class);
                return jsonMapp;
            /*ArrayList<ResultFile> list = jsonMapp.getData();
            for (ResultFile r:  list) {
                data.add(r.getName());
            }
            return data;*/
            } catch (IOException e) {
            e.printStackTrace();
            }
            return data;
            }
        }