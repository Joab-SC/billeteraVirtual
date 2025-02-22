package co.edu.uniquindio.poo;

import java.util.ArrayList;

public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<BilleteraVirtual> billeteras;

    
    public Banco(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.billeteras = new ArrayList<>();
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

    public ArrayList<BilleteraVirtual> getBilleteras() {
        return billeteras;
    }

    public void setBilleteras(ArrayList<BilleteraVirtual> billeteras) {
        this.billeteras = billeteras;
    }

    public Usuario CrearUsuario(String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena) {
        return new Usuario(nombre, direccion, numeroIdentificacion, correo, contrasena, null);
    }

    public void actualizarUsuario(Usuario usuario, String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena){
        usuario.setNombre(nombre);
        usuario.setDireccion(direccion);
        usuario.setNumeroIdentificacion(numeroIdentificacion);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
    }

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        usuarios.remove(usuario);
    }

    public void crearBilletera(BilleteraVirtual billeraVirtual){}

    public static String generarNumeroAleatorio(int digitos){
        StringBuilder numeroAleatorio = new StringBuilder();
        int digito;
        for(int i = 0; i < digitos; i++){
            digito = (int)(Math.random() * 10);
            numeroAleatorio.append(String.valueOf(digito));
        }
        return numeroAleatorio.toString();
    }
}
