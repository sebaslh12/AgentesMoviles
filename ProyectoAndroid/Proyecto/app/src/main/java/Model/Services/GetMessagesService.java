package Model.Services;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

import Model.Classes.GetMessages;
import Model.Classes.ResultMessages;

/**
 * Created by Sebas on 24/03/2016.
 */
public class GetMessagesService extends AsyncTask<String, Void, ArrayList<String>> {

    public ArrayList<String> doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/messages/{from}/{to}";
        //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";
        //String URL = "http://172.16.89.69:8191/rest/messages/{from}/{to}";
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(URL, String.class, params[0],params[1]);
        result = "{\"data\":"+ result + "}";
        System.out.println("CONSULTA>>"+result);
        try {
            GetMessages jsonMapp = new ObjectMapper().readValue(result, GetMessages.class);

            ArrayList<ResultMessages> list = jsonMapp.getData();
            for (ResultMessages r:  list) {
                data.add(r.getText());
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
