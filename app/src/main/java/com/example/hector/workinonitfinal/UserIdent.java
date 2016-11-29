package com.example.hector.workinonitfinal;

/**
 * Created by hector on 11/29/16.
 */

public class UserIdent {
    private String nombre;
    private String email;

    public UserIdent() {

    }

    public UserIdent(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
