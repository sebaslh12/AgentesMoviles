package Model.Services;

import android.os.AsyncTask;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Sebas on 25/03/2016.
 */
public class GetDownloadFile extends AsyncTask<String, Void, byte[]> {

    public byte[] doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/files/{id}";
        //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Make the HTTP GET request, marshaling the response to a String
        byte[] result = restTemplate.getForObject(URL, byte[].class, params[0]);
        //result = "{\"data\":"+ result + "}";
        //System.out.println("CONSULTA>>"+result);
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
        return result;
    }


}
