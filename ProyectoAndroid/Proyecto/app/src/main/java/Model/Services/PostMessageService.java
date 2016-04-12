package Model.Services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import Model.Classes.ResultMessages;

/**
 * Created by Sebas on 25/03/2016.
 */
public class PostMessageService extends AsyncTask<String, Void, Void> {
    public Void doInBackground(String... params) {
        // The connection URL
        String URL = "http://192.168.0.13:8191/rest/messages/";
        //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";
        //String URL = "http://172.16.89.69:8191/rest/messages";
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        ResultMessages Message = new ResultMessages();
        Message.setFrom(Integer.parseInt(params[0]));
        Message.setTo(Integer.parseInt(params[1]));
        Message.setText(params[2]);
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
        return null;
    }

}
