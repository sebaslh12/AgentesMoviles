package Model.Services;

import android.os.AsyncTask;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Sebas on 25/03/2016.
 */
public class PostUploadFileService extends AsyncTask<String, Void, String>{
    public String doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/files/{from}/{to}";
        //String URL = "http://10.0.2.2:8191/rest/files/{from}/{to}";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

        map.add("file",new FileSystemResource(params[2]));

        String result = restTemplate.postForObject(URL, map, String.class,params[0],params[1]);

        /*ByteArrayResource contentsAsResource = new ByteArrayResource(content.getBytes("UTF-8")){
            @Override
            public String getFilename(){
                return filename;
            }
        };
        map.add("file", contentsAsResource);
        String response = restTemplate.postForObject(URL, parts, String.class, authToken, path);*/


//clean-up
        //File f = new File(tempFileName);
        //f.delete();
        return result;
    }
}
