package co.edu.uniquindio.poo;

import java.time.LocalDate;
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
     * Se Inicializa la transaaccion
     */

    private Transaccion crearTransaccion(double valor, int costo, Categoria categoria,
            BilleteraVirtual billeteraDestino) {
        String codigoTransaccion = generarCodigoTransaccion();
        return new Transaccion(valor, costo, LocalDate.now(), codigoTransaccion, categoria, billeteraDestino, this);
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

        this.transacciones.add(nuevaTransaccion);
        billeteraDestino.getTransacciones().add(nuevaTransaccion);
    }

}
