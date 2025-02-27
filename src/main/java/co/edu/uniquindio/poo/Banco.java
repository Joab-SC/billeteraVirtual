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
     * Obtener las transacciones del banco -> ArrayList<Transacciones>
     */

    public ArrayList<Transaccion> obtenerTransacciones(){
        ArrayList<Transaccion> transacciones = new ArrayList<>();
        for(BilleteraVirtual billetera : billeteras){
            transacciones.addAll(billetera.getTransacciones());
        }
        return transacciones;
    }

    /**
     * Crear la billetera con sus respectivos atributos -> Billetera
     */
    public static BilleteraVirtual crearBilletera(double saldo, Usuario usuario) throws Exception {
        if(saldo < 0 ){
            throw new Exception("El saldo no puede ser negativo");
        }
        String codigo = generarNumeroAleatorio(10);
        return new BilleteraVirtual(saldo, codigo, usuario);
    }


    /**
     * Agregar una billetera a la lista de billeteras -> void
     */
    public void agregarBilletera(BilleteraVirtual billetera) throws Exception{
        if (billeteraExistente(billetera.getCodigo())){
            throw new Exception("Ya existe una billetera con ese codigo");
        }

        billeteras.add(billetera);
    }

    /**
     * Eliminar una billetera de la lista de billeteras -> void
     */
    public void eliminarBilletera(BilleteraVirtual billetera){
        billeteras.remove(billetera);
    }

    /**
     * Revisar que no hayan billeteras repetidas -> boolean
     */
    public boolean billeteraExistente(String codigo){
        for(BilleteraVirtual billetera: billeteras){
            if(billetera.getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }

    /**
     * Obtener una billetera con su usuario -> BilleteraVirtual
     */

    public BilleteraVirtual obtenerBilletera(Usuario usuario) throws Exception{
        for(BilleteraVirtual billetera: billeteras){
            if(billetera.getUsuario().equals(usuario)){
                return billetera;
            }
        }
        throw new Exception("El usuario no existe");
    }



    /**
     * Crear un usuario dados todos sus atributos -> Usuario
     */
    public static Usuario crearUsuario(String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena) {
        return new Usuario(nombre, direccion, numeroIdentificacion, correo, contrasena);
    }

    /**
     * Agregar un usuario a la lista de usuarios -> void
     */
    public void agregarUsuario(Usuario usuario) throws Exception{
        if(usuarioExistente(usuario.getNumeroIdentificacion(), usuario.getContrasena())){
            throw new Exception("Ya existe un usuario con ese id y contrasena");
        }

        usuarios.add(usuario);
    }

    /**
     * Actualizar un usuario dados todos sus atributos -> void
     */
    public void actualizarUsuario(Usuario usuarioNuevo, String nombre, String direccion, String numeroIdentificacion, String correo, String contrasena) throws Exception{
        Usuario usuarioBuscado = obtenerUsuario(usuarioNuevo.getNumeroIdentificacion(), usuarioNuevo.getContrasena());

        if(usuarioExistente(numeroIdentificacion, contrasena)){
            throw new Exception("No se puede actualizar un usuario con id y contrasena repetidas");
        }

        usuarioBuscado.setNombre(nombre);
        usuarioBuscado.setDireccion(direccion);
        usuarioBuscado.setNumeroIdentificacion(numeroIdentificacion);
        usuarioBuscado.setCorreo(correo);
        usuarioBuscado.setContrasena(contrasena);
    }


    /**
     * Eliminar un ususario de la lista de usuarios -> void
     */
    public void eliminarUsuario(Usuario usuario){
        usuarios.remove(usuario);
    }

    /**
     * Obtener un usuario con su id -> Usuario
     */
    public Usuario obtenerUsuario(String id, String contrasena) throws Exception{
        for(Usuario usuario : usuarios){
            if(usuario.getNumeroIdentificacion().equals(id)  && usuario.getContrasena().equals(contrasena)){
                return usuario;
            }
        }
        throw new Exception("El usuario no existe");
    }

    /**
     * Revisar que no usuarios repetidos -> boolean
     */
    public boolean usuarioExistente(String id, String contrasena){
        for(Usuario usuario : usuarios){
            if(usuario.getNumeroIdentificacion().equals(id) && usuario.getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
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
     * Consultar el saldo y las transacciones de una billetera dado el usuario y contrasena del usuario -> String
     */
    public  String  consultarSaldoTransacciones(String id, String constrasena) throws Exception{
        Usuario usuario = obtenerUsuario(id, constrasena);
        BilleteraVirtual billetera = obtenerBilletera(usuario);

        String consulta = "Saldo: ";

        consulta += billetera.consultarSaldo();

        consulta += "\n\nTransacciones: \n";

        consulta += billetera.consultarTransacciones();

        return consulta;

    }

}
