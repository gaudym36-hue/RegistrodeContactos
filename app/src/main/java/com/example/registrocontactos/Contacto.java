package com.example.registrocontactos;

public class Contacto {
    private String nombre;
    private String correo;
    private String telefono;
    private String tipo;

    public Contacto(String nombre, String correo, String telefono, String tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getTipo() { return tipo; }
}
