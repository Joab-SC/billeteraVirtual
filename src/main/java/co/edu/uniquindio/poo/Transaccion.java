package co.edu.uniquindio.poo;

import java.time.LocalDate;

public class Transaccion {
    private double valor;
    private int costo;
    private LocalDate fecha;
    private String codigo;
    private String categoria;
    private Usuario destinatario;
    private Usuario emisor;

    
    public Transaccion(double valor, int costo, LocalDate fecha, String codigo, String categoria, Usuario destinatario,
            Usuario emisor) {
        this.valor = valor;
        this.costo = 200;
        this.fecha = fecha;
        this.codigo = codigo;
        this.categoria = categoria;
        this.destinatario = destinatario;
        this.emisor = emisor;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public int getCosto() {
        return costo;
    }


    public void setCosto(int costo) {
        this.costo = costo;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getCategoria() {
        return categoria;
    }


    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public Usuario getDestinatario() {
        return destinatario;
    }


    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }


    public Usuario getEmisor() {
        return emisor;
    }


    public void setEmisor(Usuario emisor) {
        this.emisor = emisor;
    }

    
}
