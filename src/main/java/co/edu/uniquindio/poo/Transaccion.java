package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaccion {
    private double valor;
    private int costo;
    private LocalDateTime fecha;
    private String codigo;
    private Categoria categoria;
    private BilleteraVirtual destinatario;
    private BilleteraVirtual emisor;

    public Transaccion(double valor, LocalDateTime fecha, String codigo, Categoria categoria,
            BilleteraVirtual destinatario, BilleteraVirtual emisor) {
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
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

    public BilleteraVirtual getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(BilleteraVirtual destinatario) {
        this.destinatario = destinatario;
    }

    public BilleteraVirtual getEmisor() {
        return emisor;
    }

    public void setEmisor(BilleteraVirtual emisor) {
        this.emisor = emisor;
    }

    @Override
    public String toString() {
        return "Transaccion [valor=" + valor + ", costo=" + costo + ", fecha=" + fecha + ", codigo=" + codigo
                + ", categoria=" + categoria + ", destinatario=" + destinatario + ", emisor=" + emisor + "]";
    }

}
