package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebas on 24/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultMessages {
    //[{"id":1,"from":1,"to":2,"text":"Hola Luz","date":"2016-03-25 04:31:02"}]
    private Integer id;
    private Integer from;
    private Integer to;
    private String text;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
