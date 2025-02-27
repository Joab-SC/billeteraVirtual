import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.poo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BilleteraVirtualTest {

    private Banco banco;
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;
    private BilleteraVirtual billeteraOrigen;
    private BilleteraVirtual billeteraDestino;
    private Transaccion transaccion1;
    private Transaccion transaccion2;
    private Transaccion transaccion3;

    @BeforeEach
    public void crearDatosPrueba() throws Exception{
        // Se crea el banco y se agregan los usuarios
        banco = new Banco("Banco Test");
        usuarioOrigen = new Usuario("Origen", "Dirección 1", "111", "origen@mail.com", "pass");
        usuarioDestino = new Usuario("Destino", "Dirección 2", "222", "destino@mail.com", "pass");
        banco.agregarUsuario(usuarioOrigen);
        banco.agregarUsuario(usuarioDestino);

        // Se crean las billeteras y se agregan al banco
        billeteraOrigen = new BilleteraVirtual(100.0, "codOrigen", usuarioOrigen);
        billeteraDestino = new BilleteraVirtual(50.0, "codDestino", usuarioDestino);
        transaccion1 = new Transaccion(200.0, LocalDateTime.now(), "1234h", Categoria.VIAJES, billeteraDestino, billeteraOrigen);
        transaccion2 = new Transaccion(400.0, LocalDateTime.now(), "1234h", Categoria.GASOLINA, billeteraDestino, billeteraOrigen);
        transaccion3 = new Transaccion(2000.0, LocalDateTime.now(), "1234h", Categoria.VIAJES, billeteraOrigen, billeteraDestino);
        banco.getBilleteras().add(billeteraOrigen);
        banco.getBilleteras().add(billeteraDestino);
    }

    @Test
    public void constructorNullTest() {

        // Dado que el constructor de BilleteraVirtual no valida nulos, se espera que se
        // asignen tal cual.

        // Código nulo

        BilleteraVirtual b1 = new BilleteraVirtual(100.0, null, usuarioOrigen);
        assertNull(b1.getCodigo(), "El código debe ser nulo");

        // Usuario nulo

        BilleteraVirtual b2 = new BilleteraVirtual(100.0, "codTest", null);
        assertNull(b2.getUsuario(), "El usuario debe ser nulo");

        // La lista de transacciones se inicializa internamente, por lo que nunca es
        // nula.

        assertNotNull(b1.getTransacciones(), "La lista de transacciones nunca debe ser nula");
        assertTrue(b1.getTransacciones().isEmpty(), "La lista de transacciones debe estar vacía");
    }

    @Test
    public void valorPositivoTest() {

        // Se espera que al intentar realizar una transacción con valor negativo se
        // lance la excepción correspondiente.

        Exception exception = assertThrows(Exception.class, () -> {
            billeteraOrigen.realizarTransaccion(billeteraDestino, -10.0, 1, Categoria.VIAJES);
        });
        assertEquals("El valor de la Transacción debe ser positivo.", exception.getMessage());
    }

    @Test
    public void saldoSuficienteTest() {

        // Provocar saldo insuficiente.

        Exception exception = assertThrows(Exception.class, () -> {
            billeteraOrigen.realizarTransaccion(billeteraDestino, 200.0, 1, Categoria.FACTURAS);
        });
        assertEquals("Saldo insuficiente para realizar la transacción.", exception.getMessage());
    }

    @Test
    public void transaccionValidaTest() {

        // Se espera que una transacción con valores correctos se realice sin lanzar
        // excepción.
        assertDoesNotThrow(() -> {
            billeteraOrigen.realizarTransaccion(billeteraDestino, 50.0, 1, Categoria.GASOLINA);
        });

        /**
         * Verificar que los saldos se actualizaron correctamente:
         * Billetera origen: 100 - 50 + 1 = 49
         * Billetera destino: 50 + 50 = 100
         */

        assertEquals(49.0, billeteraOrigen.getSaldo());
        assertEquals(100.0, billeteraDestino.getSaldo());

        // Verificar que se agregó la transacción en ambas billeteras:

        assertFalse(billeteraOrigen.getTransacciones().isEmpty());
        assertFalse(billeteraDestino.getTransacciones().isEmpty());
    }

    @Test
    public void recargarBilleteraTest(){
        assertThrows(Exception.class, () -> billeteraOrigen.recargarBilletera(-23.0));
    }

    @Test
    public void obtenerTransaccionesFiltradasFechaTest(){
        Transaccion transaccion = new Transaccion(200, LocalDateTime.now(), "1234h", Categoria.VIAJES, billeteraDestino, billeteraOrigen);
    }

    @Test
    public void calcularPorcentajeGastosIngresosTotalesTest() throws Exception{

        billeteraOrigen.agregarTransaccion(transaccion1);
        billeteraOrigen.agregarTransaccion(transaccion2);
        billeteraOrigen.agregarTransaccion(transaccion3);
        billeteraDestino.agregarTransaccion(transaccion1);
        billeteraDestino.agregarTransaccion(transaccion2);
        billeteraDestino.agregarTransaccion(transaccion3);

        double valorEsperadoGastos = (600.0/2600.0)*100.0;
        System.out.println(billeteraOrigen.getTransacciones());
        System.out.println(billeteraDestino.getTransacciones());
        assertEquals(valorEsperadoGastos, billeteraOrigen.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2), true));

        double valorEsperadoIngresos = (2000.0/2600.0)*100.0;
        assertEquals(valorEsperadoIngresos, billeteraOrigen.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2), false));

    }

    @Test
    public void calcularPorcentajeGastosIngresosTotalesErrorTest() throws Exception{
        BilleteraVirtual billetera1 = new BilleteraVirtual( 5000.0, "123409890", null);

        assertThrows(Throwable.class, () -> billetera1.calcularPorcentajeGastosIngresosTotales(LocalDateTime.of(2030, 12, 8, 1,2,2), LocalDateTime.of(2000, 12, 8, 1,2,2), false));

    }

    @Test
    public void calcularPorcentajeGastosIngresosTotalesCategoriaTest() throws Exception{

        billeteraOrigen.agregarTransaccion(transaccion1);
        billeteraOrigen.agregarTransaccion(transaccion2);
        billeteraOrigen.agregarTransaccion(transaccion3);
        billeteraDestino.agregarTransaccion(transaccion1);
        billeteraDestino.agregarTransaccion(transaccion2);
        billeteraDestino.agregarTransaccion(transaccion3);

        double valorEsperadoGastos = (200.0/2200.0)*100.0;
        assertEquals(valorEsperadoGastos, billeteraOrigen.calcularPorcentajeGastosIngresosCategoria(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2),Categoria.VIAJES, true));

        double valorEsperadoIngresos = (2000.0/2200.0)*100.0;
        assertEquals(valorEsperadoIngresos, billeteraOrigen.calcularPorcentajeGastosIngresosCategoria(LocalDateTime.of(2000, 12, 8, 1,2,2), LocalDateTime.of(2030, 12, 8, 1,2,2),Categoria.VIAJES,false));

    }

    @Test
    public void obtenerTransaccionesFiltradasFecha(){

        transaccion1 = new Transaccion(200.0, LocalDateTime.of(2024, 9, 9, 12, 2, 2), "1234h", Categoria.VIAJES, billeteraDestino, billeteraOrigen);
        transaccion2 = new Transaccion(400.0, LocalDateTime.of(2022, 9, 9, 12, 2, 2), "1234h", Categoria.GASOLINA, billeteraDestino, billeteraOrigen);
        transaccion3 = new Transaccion(2000.0, LocalDateTime.of(2026, 9, 9, 12, 2, 2), "1234h", Categoria.VIAJES, billeteraOrigen, billeteraDestino);
        billeteraOrigen.agregarTransaccion(transaccion1);
        billeteraOrigen.agregarTransaccion(transaccion2);
        billeteraOrigen.agregarTransaccion(transaccion3);
        billeteraDestino.agregarTransaccion(transaccion1);
        billeteraDestino.agregarTransaccion(transaccion2);
        billeteraDestino.agregarTransaccion(transaccion3);

        var listaEsperada = List.of(transaccion1, transaccion2);
        assertIterableEquals(listaEsperada, billeteraDestino.obtenerTransaccionesFiltradasFecha(LocalDateTime.of(2020, 5, 4, 4, 4, 4), LocalDateTime.of(2025, 5, 4, 4, 4, 4)));
    }

}


