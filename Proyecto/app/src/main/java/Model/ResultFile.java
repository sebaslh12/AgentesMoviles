package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by Sebas on 25/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultFile {
    //{"id":2,"name":"1458943989597.jpg","contentType":"application/octet-stream","from":1,"to":2,"date":"2016-03-25 22:13:08"}
    private Integer id;
    private String name;
    private String contentType;
    private Integer from;
    private Integer to;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
