package co.edu.uniquindio.poo;

import java.util.ArrayList;

public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Transaccion> transacciones;

    
    public Banco(String nombre, ArrayList<Usuario> usuarios, ArrayList<Transaccion> transacciones) {
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.transacciones = transacciones;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }


    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    



}
