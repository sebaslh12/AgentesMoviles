package Model.Classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sebas on 24/03/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultUser {
    private Integer userId;
    private String userName;
    private String nombre;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}