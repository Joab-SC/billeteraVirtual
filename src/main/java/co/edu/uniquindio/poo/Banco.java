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

    /**
     * Crear un usario dados todos sus
     */
    public Usuario CrearUsuario(String nombre, String direccion, String numeroIdentificacion, String correo,
            String contrasena) {
        return new Usuario(nombre, direccion, numeroIdentificacion, correo, contrasena);
    }

    /**
     * Actualizar un usuario dados todos sus atributos -> void
     */
    public void actualizarUsuario(Usuario usuario, String nombre, String direccion, String numeroIdentificacion,
            String correo, String contrasena) {
        usuario.setNombre(nombre);
        usuario.setDireccion(direccion);
        usuario.setNumeroIdentificacion(numeroIdentificacion);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
    }

    /**
     * Agregar un usuario a la lista de ususarios -> void
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Eliminar un ususario de la lista de usuarios -> void
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    /**
     * Crear la billetera con sus respectivos atributos -> Billetera
     */
    public BilleteraVirtual crearBilletera(double saldo, Usuario usuario) throws Exception {
        String codigo = generarNumeroAleatorio(10);
        if (billeteraRepetida(codigo)) {
            throw new Exception("Ya existe una billetera con ese codigo");
        }

        return new BilleteraVirtual(saldo, codigo, usuario);
    }

    /**
     * Revisar que no hayan billeteras repetidas
     */
    public boolean billeteraRepetida(String codigo) {
        for (BilleteraVirtual billetera : billeteras) {
            if (billetera.getCodigo().equals(codigo)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generar un número aleatorio con una cantidad de digitos predeterminada ->
     * String
     */
    public static String generarNumeroAleatorio(int digitos) throws Exception {
        if (digitos <= 0) {
            throw new Exception("Los digitos deben ser mayores que 0");
        }
        StringBuilder numeroAleatorio = new StringBuilder();
        int digito;
        for (int i = 0; i < digitos; i++) {
            digito = (int) (Math.random() * 10);
            numeroAleatorio.append(String.valueOf(digito));
        }
        return numeroAleatorio.toString();
    }

}
