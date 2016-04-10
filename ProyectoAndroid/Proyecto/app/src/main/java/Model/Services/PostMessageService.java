package Model.Services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import Model.Classes.ResultMessages;

/**
 * Created by Sebas on 25/03/2016.
 */
public class PostMessageService extends AsyncTask<String, Void, ArrayList<String>> {
    public ArrayList<String> doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/messages/";
        //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        ResultMessages Message = new ResultMessages();
        Message.setFrom(1);
        Message.setTo(2);
        Message.setText("Mensajeeeeee");
        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        ResultMessages result = restTemplate.postForObject(URL,Message,ResultMessages.class);
        // Make the HTTP GET request, marshaling the response to a String
        //String result = restTemplate.getForObject(URL, String.class, params[0],params[1]);

        //System.out.println("CONSULTA>>"+result.toString());
        /*try {
            GetContacts jsonMapp = new ObjectMapper().readValue(result, GetContacts.class);

            ArrayList<ResultUser> list = jsonMapp.getData();
            for (ResultUser r:  list) {
                data.add(r.getNombre());
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return data;
    }

}
