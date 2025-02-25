import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.uniquindio.poo.Banco;
import co.edu.uniquindio.poo.BilleteraVirtual;
import co.edu.uniquindio.poo.Usuario;
import co.edu.uniquindio.poo.Categoria; // Se asume que existe esta clase o enum

public class BilleteraVirtualTest {

    private Banco banco;
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;
    private BilleteraVirtual billeteraOrigen;
    private BilleteraVirtual billeteraDestino;

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
}
