package co.edu.uniquindio.poo;
import java.util.ArrayList;

public class BilleteraVirtual {
    private double saldo;
    private String codigo;
    private ArrayList<Transaccion> transacciones;

    
    public BilleteraVirtual(double saldo, String codigo, ArrayList<Transaccion> transacciones) {
        this.saldo = saldo;
        this.codigo = codigo;
        this.transacciones = transacciones;
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



}
