package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebas on 24/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMessages {
    ArrayList<ResultMessages> data;

    public ArrayList<ResultMessages> getData() {
        return data;
    }

    public void setData(ArrayList<ResultMessages> data) {
        this.data = data;
    }
}
