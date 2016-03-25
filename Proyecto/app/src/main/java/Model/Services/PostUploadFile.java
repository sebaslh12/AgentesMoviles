package Model.Services;

import android.os.AsyncTask;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Sebas on 25/03/2016.
 */
public class PostUploadFile extends AsyncTask<String, Void, Void>{
    public Void doInBackground(String... params) {
        // The connection URL
        ArrayList<String> data = new ArrayList<String>();
        String URL = "http://192.168.0.13:8191/rest/messages/";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        final String filename = "somefile.txt";
        map.add("name", filename);
        map.add("filename", filename);
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
        return null;
    }
}
