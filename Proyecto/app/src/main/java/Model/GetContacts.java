package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Sebas on 24/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetContacts {
    private ArrayList<ResultUser> data;

    public ArrayList<ResultUser> getData() {
        return data;
    }

    public void setData(ArrayList<ResultUser> data) {
        this.data = data;
    }
}
