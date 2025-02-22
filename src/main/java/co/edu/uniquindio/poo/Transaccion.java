package co.edu.uniquindio.poo;

import java.time.LocalDate;

public class Transaccion {
    private double valor;
    private int costo;
    private LocalDate fecha;
    private String codigo;
    private String categoria;
    private BilleteraVirtual billeteraOrigen;
    private BilleteraVirtual billeteraDestinatario;

    public Transaccion(double valor, int costo, LocalDate fecha, String codigo, BilleteraVirtual billeteraOrigen,
     BilleteraVirtual billeteraDestinatario, Categoria categoria) {
        this.valor = valor;
        this.costo = 200;
        this.fecha = fecha;
        this.codigo = codigo;
        this.categoria = categoria;
        this.billeteraOrigen = billeteraOrigen;
        
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public BilleteraVirtual getBilleteraOrigen() {
        return billeteraOrigen;
    }

    public void setBilleteraOrigen(BilleteraVirtual billeteraOrigen) {
        this.billeteraOrigen = billeteraOrigen;
    }

    public BilleteraVirtual getBilleteraDestinatario() {
        return billeteraDestinatario;
    }

    public void setBilleteraDestinatario(BilleteraVirtual billeteraDestinatario) {
        this.billeteraDestinatario = billeteraDestinatario;
    }

    @Override
    public String toString() {
        return "Transaccion [valor=" + valor + ", costo=" + costo + ", fecha=" + fecha + ", codigo=" + codigo
                + ", categoria=" + categoria + ", billeteraOrigen=" + billeteraOrigen + ", billeteraDestinatario="
                + billeteraDestinatario + "]";
    }
  
    
}
