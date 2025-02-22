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
     * Agregar una billetera a la lista de billeteras -> void
     */
    public void agregarBilletera(BilleteraVirtual billetera){
        billeteras.add(billetera);
    }

    /**
     * Eliminar una billetera de la lista de billeteras -> void
     */
    public void eliminarBilletera(BilleteraVirtual billetera){
        billeteras.remove(billetera);
    }



    /**
     * Crear un usario dados todos sus atributos -> Usuario
     */
    public Usuario CrearUsuario(String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena) {
        return new Usuario(nombre, direccion, numeroIdentificacion, correo, contrasena);
    }

    /**
     * Actualizar un usuario dados todos sus atributos -> void
     */
    public void actualizarUsuario(Usuario usuarioNuevo, String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(usuarioNuevo.getNumeroIdentificacion());
        if(usuarioBuscado == null){
            throw new Exception("El uusario no existe");
        }

        usuarioBuscado.setNombre(nombre);
        usuarioBuscado.setDireccion(direccion);
        usuarioBuscado.setNumeroIdentificacion(numeroIdentificacion);
        usuarioBuscado.setCorreo(correo);
        usuarioBuscado.setContrasena(contrasena);
    }


    /**
     * Agregar un usuario a la lista de ususarios -> void
     */
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    /**
     * Eliminar un ususario de la lista de usuarios -> void
     */
    public void eliminarUsuario(Usuario usuario){
        usuarios.remove(usuario);
    }

    /**
     * Obtener un usuario con su id
     */
    public Usuario obtenerUsuario(String id){
        for(Usuario usuario : usuarios){
            if(usuario.getNumeroIdentificacion().equals(id)){
                return usuario;
            }
        }
        return null;
    }

    /**
     * Crear la billetera con sus respectivos atributos -> Billetera
     */
    public BilleteraVirtual crearBilletera(double saldo, Usuario usuario) throws Exception {
        String codigo = generarNumeroAleatorio(10);
        if (billeteraRepetida(codigo)){
            throw new Exception("Ya existe una billetera con ese codigo");
        }

        return new BilleteraVirtual(saldo, codigo, usuario);
    }

    /**
     * Revisar que no hayan billeteras repetidas
     */
    public boolean billeteraRepetida(String codigo){
        for(BilleteraVirtual billetera: billeteras){
            if(billetera.getCodigo().equals(codigo)){
                return false;
            }
        }
        return true;
    }



    /**
     * Generar un número aleatorio con una cantidad de digitos predeterminada -> String
     */
    public static String generarNumeroAleatorio(int digitos) throws  Exception{
        if(digitos <= 0){
            throw new Exception("Los digitos deben ser mayores que 0");
        }
        StringBuilder numeroAleatorio = new StringBuilder();
        int digito;
        for(int i = 0; i < digitos; i++){
            digito = (int)(Math.random() * 10);
            numeroAleatorio.append(String.valueOf(digito));
        }
        return numeroAleatorio.toString();
    }


    /**
     * Obtener una billetera con su usuario
     */

    public BilleteraVirtual obtenerBilletera(Usuario usuario){
        for(BilleteraVirtual billetera: billeteras){
            if(billetera.getUsuario().equals(usuario)){
                return billetera;
            }
        }
        return null;
    }



}
