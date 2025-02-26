package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class BilleteraVirtual {
    private double saldo;
    private String codigo;
    private ArrayList<Transaccion> transacciones;
    private Usuario usuario;

    public BilleteraVirtual(double saldo, String codigo,
            Usuario usuario) {
        this.saldo = saldo;
        this.codigo = codigo;
        this.transacciones = new ArrayList<>();
        this.usuario = usuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double consultarSaldo() {
        return getSaldo();
    }

    /**
     * Se agrega la transacción a la lista de transacciones
     */

    private void agregarTransaccion(Transaccion transaccion){
        transacciones.add(transaccion);
    }

    /**
     * se genera el UUID de la transacción
     */

    private String generarCodigoTransaccion() {
        return UUID.randomUUID().toString();
    }

    /**
     * Se consultan las transacciones sacando todos los parametros como una cadena
     */

    public String consultarTransacciones() {
        String i = "";
        for (Transaccion transaccion : transacciones) {
            i = i + transaccion.toString();
        }

        return i;

    }

    /**
     * Se verifica que tengasaldo suficiente para sealizar la transacción
     */
    private boolean saldoSuficiente(double valor, int costo) {
        return this.saldo >= (valor + costo);
    }

    /**
     * Se actualiza el saldo del Destino
     */
    private void actualizarSaldos(BilleteraVirtual billeteraDestino, double valor, int costo) {
        this.saldo -= (valor + costo);
        billeteraDestino.setSaldo(billeteraDestino.getSaldo() + valor);
    }

    /**
     * Se recarga la billetera
     */
    public void recargarBilletera(double recarga) throws Exception {
        if(recarga < 0){
            throw new Exception("Recarga Negativa");
        }
        this.saldo += recarga;
    }

    /**
     * Se Inicializa la transaaccion
     */
    private Transaccion crearTransaccion(double valor, int costo, Categoria categoria,
            BilleteraVirtual billeteraDestino) {
        String codigoTransaccion = generarCodigoTransaccion();
        return new Transaccion(valor, LocalDateTime.now(), codigoTransaccion, categoria, billeteraDestino, this);
    }

    /**
     * Se verifica que el valor enviado sea positivo
     */
    private boolean valorPositivo(double valor) {
        return valor > 0;
    }

    /**
     * Se genera la Traansaccion y se le agrega a cada billetera
     */
    public void realizarTransaccion(BilleteraVirtual billeteraDestino, double valor, int costo, Categoria categoria)
            throws Exception {
        if (!saldoSuficiente(valor, costo)) {
            throw new Exception("Saldo insuficiente para realizar la transacción.");
        }
        if (!valorPositivo(valor)) {
            throw new Exception("El valor de la Transacción debe ser positivo.");
        }

        actualizarSaldos(billeteraDestino, valor, costo);
        Transaccion nuevaTransaccion = crearTransaccion(valor, costo, categoria, billeteraDestino);

        agregarTransaccion(nuevaTransaccion);
        billeteraDestino.getTransacciones().add(nuevaTransaccion);
    }

    /**
     * Calcular el porcentaje de gastos o ingresos totales con una bandera -> double
     */
    public double calcularPorcentajeGastosIngresosTotales(LocalDateTime fechaInicio, LocalDateTime fechaFinal, boolean gastos) throws Exception {

        if(fechaInicio.isAfter(fechaFinal)){
            throw new Exception("La fecha de inicio debe de ser antes de la fecha final");
        }
        ArrayList<Transaccion> transaccionesFilatradas = obtenerTransaccionesFiltradasFecha(fechaInicio, fechaFinal);
        double ingresosTotales = 0;
        double gastosTotales = 0;

        for (Transaccion transaccion : transaccionesFilatradas) {
            if(this.equals(transaccion.getEmisor())){
                gastosTotales+=transaccion.getValor();
            }
            else if(this.equals(transaccion.getDestinatario())){
                ingresosTotales+=transaccion.getValor();
            }
        }

        double total = ingresosTotales+gastosTotales;

        if(gastos){
            return (gastosTotales/total)*100;
        }
        return (ingresosTotales/total)*100;


    }

    public double calcularPorcentajeGastosIngresosCategoria (LocalDateTime fechaInicio, LocalDateTime fechaFinal, Categoria categoria,boolean gastos) throws Exception{

        if(fechaInicio.isAfter(fechaFinal)){
            throw new Exception("La fecha de inicio debe de ser antes de la fecha final");
        }
        ArrayList<Transaccion> transaccionesFilatradas = obtenerTransaccionesFiltradasCategoria(fechaInicio, fechaFinal, categoria);
        double ingresosTotales = 0;
        double gastosTotales = 0;

        for (Transaccion transaccion : transaccionesFilatradas) {
            if(this.equals(transaccion.getEmisor())){
                gastosTotales+=transaccion.getValor();
            }
            else if(this.equals(transaccion.getDestinatario())){
                ingresosTotales+=transaccion.getValor();
            }
        }

        double total = ingresosTotales+gastosTotales;

        if(gastos){
            return (gastosTotales/total)*100;
        }
        return (ingresosTotales/total)*100;


    }

    public ArrayList<Transaccion> obtenerTransaccionesFiltradasFecha(LocalDateTime fechaInicio, LocalDateTime fechaFinal){
        ArrayList<Transaccion> transaccionesFilatradas = new ArrayList<>();
        for(Transaccion transaccion : transacciones) {
            if(transaccion.getFecha().isAfter(fechaInicio) && transaccion.getFecha().isBefore(fechaFinal)){
                transaccionesFilatradas.add(transaccion);
            }
        }
        return transaccionesFilatradas;
    }

    public ArrayList<Transaccion> obtenerTransaccionesFiltradasCategoria(LocalDateTime fechaInicio, LocalDateTime fechaFinal, Categoria categoria){
        ArrayList<Transaccion> transaccionesFiltradas = obtenerTransaccionesFiltradasFecha(fechaInicio, fechaFinal);
        ArrayList<Transaccion> transaccionesCategorias = new ArrayList<>();
        for(Transaccion transaccion: transaccionesFiltradas){
            if(transaccion.getCategoria().equals(categoria)){
                transaccionesCategorias.add(transaccion);
            }
        }
        return transaccionesCategorias;
    }

}
