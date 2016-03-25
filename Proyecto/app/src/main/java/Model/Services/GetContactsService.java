package Model.Services;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

import Model.GetContacts;
import Model.ResultUser;

/**
 * Created by Sebas on 24/03/2016.
 */
public class GetContactsService extends AsyncTask<String, Void, ArrayList<String>> {

    public ArrayList<String> doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/contacts/{idUser}";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(URL, String.class, params[0]);
        result = "{\"data\":"+ result + "}";
        System.out.println("CONSULTA>>"+result);
        try {
            GetContacts jsonMapp = new ObjectMapper().readValue(result, GetContacts.class);

            ArrayList<ResultUser> list = jsonMapp.getData();
            for (ResultUser r:  list) {
                data.add(r.getNombre());
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
